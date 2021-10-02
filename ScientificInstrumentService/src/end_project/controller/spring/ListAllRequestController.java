package end_project.controller.spring;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.TestAnalysisForm;
import LoginMJU.Student;
import Manager.ListAllRequestManager;
import net.sf.jasperreports.engine.*;

import projectutil.ConnectionDB;

@Controller
public class ListAllRequestController {

	@RequestMapping(value = "/ListAllRequestPage", method = RequestMethod.GET)
	public ModelAndView loadListAllRequestPage(HttpSession session, HttpServletRequest request) {
		Member member = null;
		Student student = null;
		Personnel personnel = null;
		try {
			student = (Student) session.getAttribute("student");
		} catch (Exception e) {
		}
		try {
			member = (Member) session.getAttribute("member");
		} catch (Exception e) {
		}
		try {
			personnel = (Personnel) session.getAttribute("personnel");
		} catch (Exception e) {
		}
		ListAllRequestManager larm = new ListAllRequestManager();
		List<FlatFeeForm> listsericeRequestForm = null;
		List<TestAnalysisForm> listTestAnalysisForm = null;
		ModelAndView mav = new ModelAndView("ListAllRequestPage");
		if (member != null) {
			listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
			listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
			mav.addObject("listsericeRequestForm", listsericeRequestForm);
			mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
			return mav;
		} else if (student != null) {
			listsericeRequestForm = larm.getListAllRequest(student.getStudentID());
			listTestAnalysisForm = larm.getListAllRequest2(student.getStudentID());
			mav.addObject("listsericeRequestForm", listsericeRequestForm);
			mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
			return mav;
		} else if (personnel != null) {
			listsericeRequestForm = larm.getListAllRequest(personnel.getPersonnel_ID());
			listTestAnalysisForm = larm.getListAllRequest2(personnel.getPersonnel_ID());
			mav.addObject("listsericeRequestForm", listsericeRequestForm);
			mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
			return mav;
		} else {
			mav.addObject("msg", "เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			ModelAndView mav2 = new ModelAndView("LoginPage");
			return mav2;
		}
	}

	@RequestMapping(value = "/Form", method = RequestMethod.GET)
	public void printpdf(HttpServletRequest request, ServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String id = request.getParameter("id");
		int typeform = Integer.parseInt(request.getParameter("typeform"));
		System.out.println(id);
		if (typeform == 1) {
			try {

				ConnectionDB condb = new ConnectionDB();
				Connection con = condb.getConection();
				String path = request.getServletContext().getRealPath("WEB-INF/PDF/F001.jrxml");
				String pathimg = request.getServletContext().getRealPath("WEB-INF/img/logosci.png");
				System.out.println(path);
				System.out.println(pathimg);
				File file = ResourceUtils.getFile(path);

				JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
				Map<String, Object> map = new HashMap<>();
				map.put("id", id);
				map.put("image_path", pathimg);
				JasperPrint print = JasperFillManager.fillReport(report, map, con);
				print.setLocaleCode("UTF-8");
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (typeform == 2) {
			try {

				ConnectionDB condb = new ConnectionDB();
				Connection con = condb.getConection();
				String path = request.getServletContext().getRealPath("WEB-INF/PDF/F110.jrxml");
				String pathimg = request.getServletContext().getRealPath("WEB-INF/img/logosci.png");
				System.out.println(path);
				System.out.println(pathimg);
				File file = ResourceUtils.getFile(path);

				JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
				Map<String, Object> map = new HashMap<>();
				map.put("sericeRequestFormNumber", id);
				map.put("image_path", pathimg);
				JasperPrint print = JasperFillManager.fillReport(report, map, con);
				print.setLocaleCode("UTF-8");
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (typeform == 3) {
			try {

				ConnectionDB condb = new ConnectionDB();
				Connection con = condb.getConection();
				String path = request.getServletContext().getRealPath("WEB-INF/PDF/F111.jrxml");
				String pathimg = request.getServletContext().getRealPath("WEB-INF/img/logosci.png");
				System.out.println(path);
				System.out.println(pathimg);
				File file = ResourceUtils.getFile(path);

				JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
				Map<String, Object> map = new HashMap<>();
				map.put("testAnalysisFormNumber", id);
				map.put("image_path", pathimg);
				JasperPrint print = JasperFillManager.fillReport(report, map, con);
				print.setLocaleCode("UTF-8");
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (typeform == 4) {
			try {

				ConnectionDB condb = new ConnectionDB();
				Connection con = condb.getConection();
				String path = request.getServletContext().getRealPath("WEB-INF/PDF/F113.jrxml");
				String pathimg = request.getServletContext().getRealPath("WEB-INF/img/logosci.png");
				System.out.println(path);
				System.out.println(pathimg);
				File file = ResourceUtils.getFile(path);

				JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
				Map<String, Object> map = new HashMap<>();
				map.put("changeFormNumber", id);
				map.put("image_path", pathimg);
				JasperPrint print = JasperFillManager.fillReport(report, map, con);
				print.setLocaleCode("UTF-8");
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
