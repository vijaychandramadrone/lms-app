package com.madrone.lms.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

import com.madrone.lms.constants.LMSConstants;
import com.madrone.lms.entity.Leave;
//import com.madrone.lms.entity.LeaveTypes;
import com.madrone.lms.form.ApplyLeaveForm;
import com.madrone.lms.form.ApplyLeaveFormGrid;
import com.madrone.lms.service.EmployeeLeaveService;
import com.madrone.lms.service.LeaveService;

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

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/applyLeave", method = RequestMethod.GET)
	public String applyLeave(Model model, ApplyLeaveForm form,
			HttpSession session)  {
		
		logger.info("Inside applyLeave()");
		final ObjectMapper mapper = new ObjectMapper();

		//To Show the Leave-Type combo box in Jsp Page
		List<Leave> ltList = leaveService.getLeaveTypes();
		model.addAttribute("leaveTypes", ltList);
		String userName = (String) session.getAttribute("sessionUser");

		// This for to Show Values in Grid.
		List<ApplyLeaveFormGrid> leaveDetailsGridList = leaveService
				.getApplyLeaveGridDetails(userName);
		String jsonString="";
		try {
			jsonString = mapper.writeValueAsString(leaveDetailsGridList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		model.addAttribute("jsonString", jsonString);
		model.addAttribute("leaveList", leaveDetailsGridList);
		model.addAttribute("ApplyLeaveForm", new ApplyLeaveForm());
		
		
		return LMSConstants.APPLY_LEAVE_SCR;
	}

	@RequestMapping(value = "/submitApplyLeave", method = RequestMethod.POST)
	public String submitApplyLeave(Model model,
			@ModelAttribute("ApplyLeaveForm") ApplyLeaveForm applyLeaveForm,
			BindingResult result, Map<String, Object> map) {
		logger.info("Inside submitApplyLeave()");
		logger.info("EMPId" + applyLeaveForm.getEmpId());

		empLeaveService.saveEmployeeLeave(applyLeaveForm);
		model.addAttribute("SucessMessage", messageSource.getMessage(
				"lms.applyLeave_success_message", new Object[] { "" },
				Locale.getDefault()));

		return LMSConstants.APPLY_LEAVE_SCR;

	}


}
