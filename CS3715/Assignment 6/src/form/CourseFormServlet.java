package form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crse.Course;
import crse.CourseNode;

public class CourseFormServlet extends HttpServlet {
	private util.XmlTemplate template;
	private String courseDataPath;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log(request.getRequestURI());
		// ensures char set is utf-8 for print writer
		response.setContentType("text/html;charset=utf-8");
		util.HTTPUtils.nocache(response);
		PrintWriter out = response.getWriter();

		//retrieve last registered student
		String user = (String) session.getAttribute("name");

		util.XmlTemplate copy = null;
		synchronized (template) {
			copy = template.makeCopy();
		}

		try {
			copy.generate(out, "html");
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		log(request.getRequestURI());
		// ensures char set is utf-8 for print writer
		response.setContentType("text/html;charset=utf-8");
		util.HTTPUtils.nocache(response);
		PrintWriter out = response.getWriter();

		// get values from form
		String courseSubj = request.getParameter("course-subject");
		String courseNum = request.getParameter("course-number");

		Course c = new Course(courseSubj, courseNum);

		if (c != null) {
			// print out list of sections for course
			out.print(c.getSubj() + c.getNum());
			out.print("<ul>");
			for (CourseNode cn : c.getClassList()) {
				out.print(cn.getSections());
			}
			out.print("</ul>");
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // super.init call is required
		try {
			courseDataPath = getInitParameter("course-data");
			if (courseDataPath == null) {
				courseDataPath = "war/course.data";
			}
			String formPath = getInitParameter("course-form");
			if (formPath == null) {
				formPath = "war/course.html";
			}
			template = new util.XmlTemplate(formPath);
		} catch (Exception ex) {
			log(ex.getMessage());
			throw new ServletException(ex);
		}
	}

}