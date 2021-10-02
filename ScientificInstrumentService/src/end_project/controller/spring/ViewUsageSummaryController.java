package end_project.controller.spring;

import java.sql.Date;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.Personnel;
import Manager.ViewUsageSummaryManager;

@Controller
public class ViewUsageSummaryController {

	@RequestMapping(value = "/loadUsageSummary", method = RequestMethod.GET)
	public ModelAndView loadUsageSummary(HttpSession session, HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ModelAndView mav = new ModelAndView("ViewUsageSummaryPage");
			return mav;
		} else {
			ModelAndView mav =  new ModelAndView("LoginPage");
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/searchSummary", method = RequestMethod.POST)
	public ModelAndView searchSummary(HttpSession session, HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		ModelAndView mav = new ModelAndView("ViewUsageSummaryPage");
		if (personnel != null) {
			String startdate =request.getParameter("startdate") + " 00:00:00";
			String enddate = request.getParameter("enddate")+ " 00:00:00";
			
			System.out.println(startdate);
			System.out.println(enddate);
			ViewUsageSummaryManager vusm = new ViewUsageSummaryManager();
			ResultSet rs = vusm.getsummary(startdate,enddate );
			ResultSet rs2 = vusm.getsummarybybranch(startdate,enddate );
			
			mav.addObject("startdate",startdate.substring(0, startdate.indexOf(" ")));
			mav.addObject("enddate",enddate.substring(0, startdate.indexOf(" ")));
			mav.addObject("rs",rs);
			mav.addObject("rs2",rs2);
			
		
			return mav;
		} else {
			return mav =  new ModelAndView("LoginPage");
		}
	}

}
