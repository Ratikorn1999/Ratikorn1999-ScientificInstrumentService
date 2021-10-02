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
import Manager.EditInstrumentsDetailsManager;
import Manager.ViewInstrumentsDetailsManager;

@Controller
public class EditInstrumentsDetailsController {

	
	@RequestMapping(value = "/do_EditInstrumentsDetails", method = RequestMethod.POST)
	public String do_EditInstrumentsDetails(HttpServletRequest request, Model model, HttpSession session)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		String sciInstruments_ID = null;
		if (personnel != null) {
			request.setCharacterEncoding("UTF-8");
			if (ServletFileUpload.isMultipartContent(request)) {
				request.setCharacterEncoding("UTF-8");
				try {
					String key =(String) session.getAttribute("key");
					List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					String filename = new File(data.get(0).getName()).getName();
					System.out.println("filename :" + filename);
					 sciInstruments_ID = data.get(1).getString("UTF-8");
					System.out.println("sciInstruments_ID :" + sciInstruments_ID);
					String sciInstruments_Name = data.get(2).getString("UTF-8");
					System.out.println("sciInstruments_Name :" + sciInstruments_Name);
					String sciInstrumentsType_ID = data.get(3).getString("UTF-8");
					System.out.println("sciInstrumentsTypeid :" + sciInstrumentsType_ID);
					String detail = data.get(4).getString("UTF-8");
					System.out.println("detail :" + detail);
					String staffid = data.get(5).getString("UTF-8");
					System.out.println("staffid :" + staffid);
					String coursepresidentid = data.get(6).getString("UTF-8");
					System.out.println("coursepresidentid :" + coursepresidentid);
					
					String status = null;
					System.out.println("status :" + status);
					Double priceType_A = Double.parseDouble(data.get(7).getString("UTF-8"));
					System.out.println("priceType_A :" + priceType_A);
					Double priceType_B = Double.parseDouble(data.get(8).getString("UTF-8"));
					System.out.println("priceType_B :" + priceType_B);
					Double priceType_C = Double.parseDouble(data.get(9).getString("UTF-8"));
					System.out.println("priceType_C :" + priceType_C);
					Double priceType_D = Double.parseDouble(data.get(10).getString("UTF-8"));
					System.out.println("priceType_D :" + priceType_D);
					Double priceFlatFee = 0.0;
					try {
						priceFlatFee = 	Double.parseDouble(data.get(11).getString("UTF-8"));		
					} catch (Exception e) {
								// TODO: handle exception
					}
					System.out.println("priceFlatFee :" + priceFlatFee);
					
					
					
					
					Staff staff = new Staff();
					staff.setStaffid(staffid);
					Coursepresident coursepresident = new Coursepresident();
					coursepresident.setCoursepresidentid(coursepresidentid);
					SciInstrumentsType sciInstrumentsType = new SciInstrumentsType();
					sciInstrumentsType.setSciInstrumentsType_ID(sciInstrumentsType_ID);
					SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID,sciInstruments_Name,status,detail,priceType_A,priceType_B,priceType_C,priceType_D,priceFlatFee);
					sciInstruments.setSciInstrumentsType(sciInstrumentsType);
					sciInstruments.setCoursepresident(coursepresident);
					sciInstruments.setStaff(staff);
					
					EditInstrumentsDetailsManager eidm = new EditInstrumentsDetailsManager();
					boolean statusresult = eidm.isEditInstrumentsDetails(key,sciInstruments);

					if (statusresult||!filename.equals(null)) {
						/*
						 * String path = request.getSession().getServletContext().getRealPath("/") +
						 * "WEB-INF\\img\\sciInstruments_img\\"; data.get(0).write(new
						 * File(path.toString() + File.separator + sciInstruments_ID + ".png"));
						 */
						data.get(0).write(new File("/usr/share/apache-tomcat-9.0.0.M21/webapps/ScientificInstrumentService/WEB-INF/img/sciInstruments_img/"+ sciInstruments_ID + ".png"));
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			ViewInstrumentsDetailsManager vimem = new ViewInstrumentsDetailsManager(); 
			SciInstruments sciInstruments2 = vimem.getInstrumentsByID(sciInstruments_ID) ;
			request.setAttribute("sciInstruments", sciInstruments2);
			session.removeAttribute("key");
			return "ViewInstrumentsDetailsPage";
		} else {
			return "LoginPage";
		}
		
	}
	
}
