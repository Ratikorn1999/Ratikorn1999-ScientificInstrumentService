package end_project.controller.spring;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.SciInstruments;
import Manager.DeleteInstrumentsManager;
import Manager.ListSciInstrumentsManager;

@Controller
public class DeleteInstrumentsController {


	@RequestMapping(value = "/do_DeleteInstruments", method = RequestMethod.GET)
	public String do_DeleteInstruments(HttpSession session, HttpServletRequest request) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		String  sciInstruments_ID =  request.getParameter("sciInstruments_ID");
		if (personnel != null) {
			
			DeleteInstrumentsManager dtim = new DeleteInstrumentsManager();
			boolean status = dtim.isDeleteInstruments(sciInstruments_ID);
			System.out.println("Status Delete is "+status);
			if(status) {
			File file = new File("/usr/share/apache-tomcat-9.0.0.M21/webapps/ScientificInstrumentService/WEB-INF/img/sciInstruments_img/"+ sciInstruments_ID + ".png");
	        if(file.delete()) {
	       System.out.println("File deleted successfully");
	        } else{
	            System.out.println("Failed to delete the file");
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
