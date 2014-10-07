import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;

import static java.lang.String.valueOf;

/**
 * 
 * Simple patient data form.
 * 
 * @author Rod Byrne
 */
public class StudentFormServlet extends HttpServlet {
	private util.XmlTemplate template;
	private String patientDataPath;
	private Gson gson = new Gson();

	private void storePatientData(med.Patient p) {
		try {
			synchronized (patientDataPath) {
				FileOutputStream fs = new FileOutputStream(patientDataPath);
				ObjectOutputStream oos = new ObjectOutputStream(fs);
				oos.writeObject(p);
				oos.close();
				// XXX closing oos on Exception
			}
		} catch (Exception ex) {
			log(ex.getMessage());
		}
	}

	private med.Patient retrievePatientData() {
		try {
			synchronized (patientDataPath) {
				File f = new File(patientDataPath);
				if (!f.exists()) {
					return null;
				}
				FileInputStream fs = new FileInputStream(f);
				ObjectInputStream ios = new ObjectInputStream(fs);
				med.Patient p = (med.Patient) ios.readObject();
				ios.close();
				return p;
				// XXX closing ios on Exception
			}
		} catch (Exception ex) {
			log(ex.getMessage());
		}
		return null;
	}

	// @snipit formJSON.PatientFormServlet.doGet
	// no query is handled in this version
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log(request.getRequestURI());
		// ensures char set is utf-8 for print writer
		response.setContentType("text/html;charset=utf-8");
		util.HTTPUtils.nocache(response);
		PrintWriter out = response.getWriter();

		util.XmlTemplate copy = null;
		synchronized (template) {
			copy = template.makeCopy();
		}

		med.Patient p = retrievePatientData();
		if (p != null) {
			updateForm(copy, p);
		}

		try {
			copy.generate(out, "html");
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
	}

	// @snipit-end formJSON.PatientFormServlet.doGet

	// @snipit formJSON.PatientFormServlet.doPost
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log(request.getRequestURI());
		response.setContentType("application/json; charset=utf-8");
		util.HTTPUtils.nocache(response);
		PrintWriter out = response.getWriter();
		BufferedReader rd = request.getReader();

		// XXX handle JsonParseException
		med.Patient p = gson.fromJson(rd, med.Patient.class);
		System.out.println("patient = " + p);
		storePatientData(p);

		// echo the reply
		String json = gson.toJson(p);
		out.print(json);

		rd.close();
		out.close();
	}

	// @snipit-end formJSON.PatientFormServlet.doPost

	// @snipit formJSON.PatientFormServlet.init
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // super.init call is required
		try {
			patientDataPath = getInitParameter("patient-data");
			if (patientDataPath == null) {
				patientDataPath = "war/patient.data";
			}
			String formPath = getInitParameter("patient-form");
			if (formPath == null) {
				formPath = "war/pat-form-json.html";
			}
			template = new util.XmlTemplate(formPath);
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
	}

	// @snipit-end formJSON.PatientFormServlet.init

	private void updateForm(util.XmlTemplate copy, med.Patient p) {
		copy.setAttribute("patient-name", "value", p.getName());
		copy.setAttribute("patient-age", "value", valueOf(p.getAge()));
		copy.setAttribute("patient-weight", "value", valueOf(p.getWeight()));
		copy.setAttribute("patient-height", "value", valueOf(p.getHeight()));
	}
}