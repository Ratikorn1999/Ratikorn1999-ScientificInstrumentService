package end_project.controller.spring;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Coursepresident;
import Class_End_Project.Personnel;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.Staff;
import Manager.AddInstrumentsManager;
import Manager.ListSciInstrumentsManager;

@Controller
public class AddInstrumentsController {

	@RequestMapping(value = "/loadAddInstrumentsPage", method = RequestMethod.GET)
	public String loadAddInstrumentsPage(HttpSession session, HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			return "AddInstrumentsPage";
		} else {
			return "LoginPage";
		}
	}

	@RequestMapping(value = "/doAddinstruments", method = RequestMethod.POST)
	public String doAddinstruments(HttpServletRequest request, Model model, HttpSession session)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			request.setCharacterEncoding("UTF-8");
			if (ServletFileUpload.isMultipartContent(request)) {
				request.setCharacterEncoding("UTF-8");
				try {
					List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					
					String sciInstruments_ID = data.get(0).getString("UTF-8");
					System.out.println("sciInstruments_ID :" + sciInstruments_ID);
					String sciInstruments_Name = data.get(1).getString("UTF-8");
					System.out.println("sciInstruments_Name :" + sciInstruments_Name);
					String sciInstrumentsType_ID = data.get(2).getString("UTF-8");
					System.out.println("sciInstrumentsTypeid :" + sciInstrumentsType_ID);
					String detail = data.get(3).getString("UTF-8");
					System.out.println("detail :" + detail);
					String staffid = data.get(4).getString("UTF-8");
					System.out.println("staffid :" + staffid);
					String coursepresidentid = data.get(5).getString("UTF-8");
					System.out.println("coursepresidentid :" + coursepresidentid);
					String status = data.get(6).getString("UTF-8");
					System.out.println("status :" + status);
					Double priceType_A = Double.parseDouble(data.get(8).getString("UTF-8"));
					System.out.println("priceType_A :" + priceType_A);
					Double priceType_B = Double.parseDouble(data.get(9).getString("UTF-8"));
					System.out.println("priceType_B :" + priceType_B);
					Double priceType_C = Double.parseDouble(data.get(10).getString("UTF-8"));
					System.out.println("priceType_C :" + priceType_C);
					Double priceType_D = Double.parseDouble(data.get(11).getString("UTF-8"));
					System.out.println("priceType_D :" + priceType_D);
					Double priceFlatFee = 0.0;
					try {
						priceFlatFee = 	Double.parseDouble(data.get(12).getString("UTF-8"));		
					} catch (Exception e) {
								// TODO: handle exception
					}
					
					System.out.println("priceFlatFee :" + priceFlatFee);
					
					SciInstrumentsType sciInstrumentsType = new SciInstrumentsType();
					sciInstrumentsType.setSciInstrumentsType_ID(sciInstrumentsType_ID);
					Staff staff = new Staff();
					staff.setStaffid(staffid);
					Coursepresident coursepresident = new Coursepresident();
					coursepresident.setCoursepresidentid(coursepresidentid);
					SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID,sciInstruments_Name,status,detail,priceType_A,priceType_B,priceType_C,priceType_D,priceFlatFee);
					sciInstruments.setSciInstrumentsType(sciInstrumentsType);
					sciInstruments.setCoursepresident(coursepresident);
					sciInstruments.setStaff(staff);
					
					
					AddInstrumentsManager aimm = new AddInstrumentsManager();
					boolean statusadd = aimm.isAddinstruments(sciInstruments);
					System.out.println(statusadd);
					if (statusadd) {
						data.get(7).write(new File("/usr/share/apache-tomcat-9.0.0.M21/webapps/ScientificInstrumentService/WEB-INF/img/sciInstruments_img/"+ sciInstruments_ID + ".png"));
					}else {
						return "AddInstrumentsPage";
					}
					

				} catch (Exception e) {
				}

			}

			ListSciInstrumentsManager vism = new ListSciInstrumentsManager();
			List<SciInstruments> listsciInstruments = vism.getListSciInstruments();
			request.setAttribute("listsciInstruments", listsciInstruments);
			return "ListSciInstrumentsPage";

		} else {
			return "LoginPage";
		}

	}
}
