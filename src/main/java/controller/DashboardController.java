package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
	
	@RequestMapping(value="/school", method=RequestMethod.GET)
	public ModelAndView getDashboard() {
		ModelAndView model = new ModelAndView("dashboard-page");
		return model;
	}

}
