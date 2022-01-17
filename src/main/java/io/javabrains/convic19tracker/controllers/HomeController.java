package io.javabrains.convic19tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.convic19tracker.models.LocationStats;
import io.javabrains.convic19tracker.services.CovidDataService;

@Controller
public class HomeController {
	
	@Autowired
	CovidDataService covidDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = covidDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLastestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousStats()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
