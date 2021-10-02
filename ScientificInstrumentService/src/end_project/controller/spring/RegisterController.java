package end_project.controller.spring;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.*;
import Manager.*;
@Controller
public class RegisterController {

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public ModelAndView registeruser(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
	
		request.setCharacterEncoding("UTF-8");
	
		String username = request.getParameter("username");
		String password = request.getParameter("conpwd");
		String checktypeMember = request.getParameter("typeMember");
		String position = null;
		String member_Prefix = request.getParameter("prefixuser");
		String member_FirstName = request.getParameter("fname");
		String member_LastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phonenumber");
		String company_name = null;
		String studentID = null;
		String degree = null;
		String branch = null;
		String faculty = null;
		String university = null;
		String advisor_name = null;
		String emailadvisor = null;
		
		String typeMember = "";
		if (checktypeMember.equals("3")) {
			typeMember = "ก";
			position = "หน่วยงานภายนอก";
			company_name = request.getParameter("company_name");
		} else {
			if (checktypeMember.equals("1")) {
				position = "นักศึกษา";
				studentID = request.getParameter("studentID");
				degree = request.getParameter("degree");
				advisor_name = request.getParameter("advisor_name");
				emailadvisor = request.getParameter("emailadvisor");
				branch = request.getParameter("branch");
				faculty = request.getParameter("faculty");
				university = request.getParameter("university");
			} else {
				position = "อาจารย์";
				branch = request.getParameter("branch_Personnel");
				faculty = request.getParameter("faculty_Personnel");
				university = request.getParameter("university_Personnel");
			}
			typeMember = "ข";
		}

		RegisterManager rm = new RegisterManager();
		String member_ID = rm.getnextmemberid();

		member_ID = member_ID.substring(1);
		int number = member_ID.length();
		int no = Integer.parseInt(member_ID) + 1;
		String length = "" + no;
		member_ID = "M";
		number = number - length.length();
		for (int i = 0; i < number; i++) {
			member_ID = member_ID + "0";
		}
		member_ID = member_ID + no;

		Member member = new Member(member_ID, member_Prefix, member_FirstName, member_LastName, email, typeMember,
				position, phonenumber, studentID, degree, branch, faculty, university, advisor_name, emailadvisor,
				company_name);

		Login login = new Login(username, password);
		member.setLogin(login);

		boolean status = rm.register(member);
		System.out.println("status is "+status);
		if (status) {
			ModelAndView mav = new ModelAndView( "LoginPage");
			mav.addObject("msg","สมัครสมาชิกสำเร็จ !!!!");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView( "RegisterPage");
			mav.addObject("m",member);
			mav.addObject("error_msg","สมัครสมาชิกไม่สำเร็จ !!!!");
			return mav;
		}

	}

}
