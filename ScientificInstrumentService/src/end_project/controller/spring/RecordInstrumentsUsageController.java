package end_project.controller.spring;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Schedule;
import Manager.RecordInstrumentsUsageManager;
import Manager.ViewUsageScheduleManager;



@Controller
public class RecordInstrumentsUsageController {

	
	@RequestMapping(value = "/doAddinstrumentsusage", method = RequestMethod.POST)
	public String doAddinstrumentsusage(HttpServletRequest request) {
		String formnumber = request.getParameter("formnumber");
		String[] additionalcost =request.getParameterValues("additionalcost") ;
		String[] schedule_ID = request.getParameterValues("schedule_ID");
		List<Schedule> listschedule = new Vector<>();
		for(int i = 0 ; i < schedule_ID.length ; i++) {
			Schedule schedule  = new Schedule ();
			schedule.setSchedule_ID(schedule_ID[i]);
			schedule.setAdditionalcost(Double.parseDouble(additionalcost[i]));
			listschedule.add(schedule);
		}
		RecordInstrumentsUsageManager rium = new RecordInstrumentsUsageManager();
		boolean result = rium.isInstrumentsusage(listschedule);
		
		
		ViewUsageScheduleManager vusm = new ViewUsageScheduleManager();
		FlatFeeForm flatFeeForm = vusm.getUsageschedule(formnumber);
		request.setAttribute("flatFeeForm", flatFeeForm); 
		return "ViewUsageSchedulePage";
	}
	
}
