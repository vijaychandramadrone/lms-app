package com.madrone.lms.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.madrone.lms.constants.LMSConstants;
import com.madrone.lms.entity.EmployeeLeave;
import com.madrone.lms.entity.Leave;
//import com.madrone.lms.entity.LeaveTypes;
import com.madrone.lms.form.ApplyLeaveFormGrid;
import com.madrone.lms.form.LeaveForm;
import com.madrone.lms.service.EmployeeLeaveService;
import com.madrone.lms.service.LeaveService;
import com.madrone.lms.utils.JSONUtils;

@Controller
public class ApplyLeaveController {
	private static final Logger logger = LoggerFactory
			.getLogger(ApplyLeaveController.class);

	@Autowired
	private EmployeeLeaveService empLeaveService;

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = "/applyLeave", method = RequestMethod.GET)
	public String applyLeave(Model model, LeaveForm form,
			HttpSession session)  {
		logger.info("Inside applyLeave()");
		

		//To Show the Leave-Type combo box in Jsp Page
		List<Leave> ltList = leaveService.getLeaveTypes();
		session.setAttribute("leavetypes", ltList);
		model.addAttribute("leaveTypes", ltList);
		String userName = (String) session.getAttribute("sessionUser");

		// This for to Show Values in Grid.
		List<ApplyLeaveFormGrid> gridList = leaveService
				.getApplyLeaveGridDetails(userName);
		String jsonString= JSONUtils.convertListToJson(gridList);
		model.addAttribute("jsonString", jsonString);
		model.addAttribute("leaveList", gridList);
		model.addAttribute("ApplyLeaveForm", new LeaveForm());
		
	   	return LMSConstants.APPLY_LEAVE_SCR+"_" +  
	   		session.getAttribute("sessionRole");
	}

	@RequestMapping(value = "/submitApplyLeave", method = RequestMethod.POST)
	public ModelAndView submitApplyLeave(@ModelAttribute("ApplyLeaveForm") LeaveForm applyLeaveForm,BindingResult result, Map<String, Object> map, HttpSession session, RedirectAttributes ra) {
		
		ModelAndView modelView = new ModelAndView(new RedirectView(LMSConstants.APPLY_LEAVE_URL));
		String operation = "APPLY";
		EmployeeLeave el  = empLeaveService.setBeanValuesForSave(applyLeaveForm,operation);
		empLeaveService.saveEmployeeLeave(el);
		ra. addFlashAttribute("SucessMessage", messageSource.getMessage(
				"lms.applyLeave_success_message", new Object[] { "" },
				Locale.getDefault()));
		return modelView;
	}
}
