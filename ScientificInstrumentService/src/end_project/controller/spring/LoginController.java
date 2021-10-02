package end_project.controller.spring;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.Member;
import Class_End_Project.Personnel;
import LoginMJU.AuthLogin;
import LoginMJU.PersonnelMJU;
import LoginMJU.Student;
import Manager.LoginManager;


@Controller
public class LoginController {

	@RequestMapping(value = "/varifylogin", method = RequestMethod.POST)
	public ModelAndView varifylogin(HttpServletRequest request, Model model, HttpSession session) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginManager logmgr = new LoginManager();

		if (username.contains("@")) {
			Personnel personnel = logmgr.doLoginAdmin(username);

			ModelAndView mav = new ModelAndView("index");
			session.setAttribute("personnel", personnel);
			return mav;
		} else {

			Member member = logmgr.doLogin(username, password);

			if (member == null) {
				ModelAndView mav = new ModelAndView("LoginPage");
				System.out.println("*******null********");

				mav.addObject("error_msg", "กรุณาป้อนชื่อผู้ใช้และรหัสผ่านให้ถูกต้อง");

				// request.setAttribute("error_msg", true);
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("index");
				session.setAttribute("member", member);
				return mav;
			}
		}

	}

	@RequestMapping(value = "/varifylogout", method = RequestMethod.GET)
	public ModelAndView varifylogout(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("index");
		session.removeAttribute("member");
		session.removeAttribute("student");
		session.removeAttribute("personnel");

		return mav;
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public ModelAndView doLoginMJU(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws SQLException, IOException {
		ModelAndView mav = new ModelAndView("index");
		AuthLogin auths = new AuthLogin();
		String websiteToken = "6d42ae5e2a6545b69228f2b606cf9ffe";
		String soapAction = "http://www.passport.mju.ac.th/CitizenID";
		String loginToken = request.getParameter("T");
		String isStudent = request.getParameter("Student");

		if (isStudent.equals("False")) {
			auths.setUserrole("staff");
		} else {
			auths.setUserrole("student");
		}
		// System.out.println(loginToken);

		String responseString;
		String outputString = "";
		try {

			// System.out.println("Checking 1 *****************");
			String WSurl = "";
			String envelope = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "  <soap:Body>" + "    <CitizenID xmlns=\"http://www.passport.mju.ac.th/\">"
					+ "      <WebsiteToken>" + websiteToken + "</WebsiteToken>" + "      <LoginToken>" + loginToken
					+ "</LoginToken>" + "    </CitizenID>" + "  </soap:Body>" + "</soap:Envelope>";
			// refer soap ui for above input(String envelope="....")

			WSurl = "https://passport.mju.ac.th/login.asmx?wsdl";

			URL url = new URL(WSurl);
			java.net.URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			// System.out.println("Checking 2 *****************");
			byte[] buffer = new byte[envelope.length()];
			buffer = envelope.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			// Set the appropriate HTTP parameters.
			httpConn.setReadTimeout(60000);
			httpConn.setConnectTimeout(60000);
			// System.out.println("Checking 3 *****************");
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", soapAction);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);

			// System.out.println("Checking 4*****************");
			OutputStream out = httpConn.getOutputStream();
			out.write(b);
			out.close();

			// System.out.println("Checking 5*****************");
			// Ready with sending the request.
			// Read the response.
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			// System.out.println("Checking 6*****************");

			if (outputString.contains("<CitizenIDResult>")) {
				auths.setResult(true);
				String idcard = outputString.substring(outputString.indexOf("<CitizenIDResult>") + 17,
						outputString.indexOf("<CitizenIDResult>") + 30);

				// System.out.println("*******************"+isStudent+"*******************");
				LoginManager logmgr = new LoginManager();
				if (isStudent.equals("False")) {

					PersonnelMJU staff = this.getStaffProfile(idcard);
					logmgr.insertPersonnel(staff);
					Personnel personnel = logmgr.doLoginAdmin(staff.getE_mail());

					session.setAttribute("personnel", personnel);

				} else {
					System.out.println("Get Student");
					Student student = this.getStudentProfile(idcard);
					logmgr.checkStudent(student);

					Student stu = logmgr.doLoginStudent(student.getStudentID());
					session.setAttribute("student", stu);
				}

			} else {
				auths.setResult(false);

			}
			session.setAttribute("checkauth", auths);
			// System.out.println("Checking 7*****************" + outputString);

		} catch (Exception e) {
			System.out.println("********Stack Trace starts**********" + " ");
			e.printStackTrace();
			System.out.println("********Stack Trace ends**********");

			outputString = "ERROR";
		}

		return mav;
	}

	public PersonnelMJU getStaffProfile(String idcard) {

		String url = "https://api.mju.ac.th/Person/API/PERSON9486bba19bca462da44dc8ac447dea9723052020/" + idcard;

		InputStream is = null;
		try {
			URL staff = new URL(url); // URL to Parse

			HttpURLConnection conn = (HttpURLConnection) staff.openConnection();
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");

			conn.setDoInput(true);
			conn.connect();

			is = conn.getInputStream();

			String result = convertInputStreamToString(is);
			// System.out.println(result);
			JSONArray jsonarr = new JSONArray(result);
			JSONObject jsonobj = jsonarr.getJSONObject(0);
			String positionCode = jsonobj.getString("positionCode");
			String titlePosition = jsonobj.getString("titlePosition");
			String firstName = jsonobj.getString("firstName");
			String lastName = jsonobj.getString("lastName");
			String personnelType = jsonobj.getString("personnelType");
			String positionType = jsonobj.getString("positionType");
			String position = jsonobj.getString("position");
			String faculty = jsonobj.getString("faculty");
			String e_mail = jsonobj.getString("e_mail");

			PersonnelMJU staffbean = new PersonnelMJU(positionCode, titlePosition, firstName, lastName, personnelType,
					positionType, position, faculty, e_mail);

			is.close();
			return staffbean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Student getStudentProfile(String idcard) {

		String url = "https://api.mju.ac.th/Student/API/STUDENTe8ee4f3759cc4763a8f231965a2da6db23052020/CitizenID/"
				+ idcard;

		InputStream is = null;
		try {
			URL staff = new URL(url); // URL to Parse

			HttpURLConnection conn = (HttpURLConnection) staff.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();

			is = conn.getInputStream();

			String result = convertInputStreamToString(is);
			JSONArray jsonarr = new JSONArray(result);
			System.out.println(jsonarr);
			JSONObject jsonobj = jsonarr.getJSONObject(0);

			String studentID = jsonobj.getString("studentID");
			// System.out.println("studentID is "+studentID);
			String nameTh = jsonobj.getString("nameTh");
			// System.out.println("nameTh is "+nameTh);
			String surnameTh = jsonobj.getString("surnameTh");
			// System.out.println("surnameTh is "+surnameTh);
			String programNameTh = jsonobj.getString("programNameTh");
			// System.out.println("programNameTh is "+programNameTh);
			String facultyNameTh = jsonobj.getString("facultyNameTh");
			// System.out.println("facultyNameTh is "+facultyNameTh);
			String statusName = jsonobj.getString("statusName");
			// System.out.println("statusName is "+statusName);
			String status = jsonobj.getString("status");
			// System.out.println("status is "+status);
			String levelName = jsonobj.getString("levelName");
			// System.out.println("levelName is "+levelName);

			// System.out.println("idcard is "+idcard);
			Student stubean = new Student(studentID, nameTh, surnameTh, programNameTh, facultyNameTh, statusName,
					status, levelName, idcard);

			is.close();
			return stubean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String convertInputStreamToString(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
		return sb.toString();
	}

	@RequestMapping(value = "/doLoginsss", method = RequestMethod.GET)
	public ModelAndView doLogins(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws SQLException, IOException {
		ModelAndView mav = new ModelAndView("index");

		return mav;
	}
}
