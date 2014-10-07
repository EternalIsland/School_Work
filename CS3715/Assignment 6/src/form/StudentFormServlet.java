/**
 * Taken from CS3715 notes (by Rod Byrne) and modified.
 */

package form;

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
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;

import stu.Student;
import static java.lang.String.valueOf;

/**
 * 
 * Simple student data form.
 * 
 * @author Rod Byrne (Modified by Matthew Hynes)
 */
public class StudentFormServlet extends HttpServlet {
	private util.XmlTemplate template;
	private String studentDataPath;
	private Gson gson = new Gson();
	private String nameID;
	private HashMap<String, stu.Student> students;

	private void storeStudentData(stu.Student s) {
		try {
			synchronized (studentDataPath) {
				FileOutputStream fs = new FileOutputStream(studentDataPath);
				ObjectOutputStream oos = new ObjectOutputStream(fs);
				oos.writeObject(s);
				oos.close();
			}
		} catch (Exception ex) {
			log(ex.getMessage());
		}
		students.put(s.getEmail(), s);
	}

	private stu.Student retrieveStudentData() {
		try {
			synchronized (studentDataPath) {
				File f = new File(studentDataPath);
				if (!f.exists()) {
					return null;
				}
				FileInputStream fs = new FileInputStream(f);
				ObjectInputStream ios = new ObjectInputStream(fs);
				stu.Student s = (stu.Student) ios.readObject();
				ios.close();
				return s;
				// XXX closing ios on Exception
			}
		} catch (Exception ex) {
			log(ex.getMessage());
		}
		return null;
	}

	// @snipit form.StudentFormServlet.doGet
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

		stu.Student s = retrieveStudentData();
		if (s != null) {
			updateForm(copy, s);
			nameID = s.getEmail();
			manageSession(session);
		}

		try {
			copy.generate(out, "html");
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
	}

	// @snipit-end form.StudentFormServlet.doGet

	// manage student id (email) with session
	private void manageSession(HttpSession session) {
		session.setAttribute("name", nameID);
		log((String) session.getAttribute("name"));
	}

	// @snipit form.StudentFormServlet.doPost
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log(request.getRequestURI());
		response.setContentType("application/json; charset=utf-8");
		util.HTTPUtils.nocache(response);
		PrintWriter out = response.getWriter();
		BufferedReader rd = request.getReader();

		// XXX handle JsonParseException
		stu.Student s = gson.fromJson(rd, stu.Student.class);
		nameID = s.getEmail();
		manageSession(session);
		System.out.println("student = " + s);
		storeStudentData(s);

		// echo the reply
		String json = gson.toJson(s);
		out.print(json);

		rd.close();
		out.close();
	}

	// @snipit-end form.StudentFormServlet.doPost

	// @snipit form.StudentFormServlet.init
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // super.init call is required
		try {
			studentDataPath = getInitParameter("student-data");
			if (studentDataPath == null) {
				studentDataPath = "war/student.data";
			}
			String formPath = getInitParameter("student-form");
			if (formPath == null) {
				formPath = "war/student.html";
			}
			template = new util.XmlTemplate(formPath);
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
		students = new HashMap<String, stu.Student>();
	}

	// @snipit-end form.StudentFormServlet.init

	private void updateForm(util.XmlTemplate copy, stu.Student s) {
		copy.setAttribute("student-firstname", "value", s.getFirstName());
		copy.setAttribute("student-lastname", "value", valueOf(s.getLastName()));
		copy.setAttribute("student-number", "value", valueOf(s.getStudentNo()));
		copy.setAttribute("student-email", "value", valueOf(s.getEmail()));
		copy.setAttribute("student-mail", "value", valueOf(s.getMailing()));
	}
}
