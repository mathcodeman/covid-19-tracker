package io.javabrains.convic19tracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.javabrains.convic19tracker.models.LocationStats;


@Service
public class CovidDataService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	//Created an array to store LocationStats object
	private List<LocationStats> allStats = new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	@PostConstruct
	@Scheduled(cron="* * * * * *")
	public void fetchVirusData() {
		List<LocationStats> newStats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		
		try {
			HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Refreshed!!");
			StringReader csvBodyReader = new StringReader(httpResponse.body());
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
			
			for (CSVRecord record : records) {
				
				LocationStats locationStats = new LocationStats();
				
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry(record.get("Country/Region"));
				int lastestCases = Integer.parseInt(record.get(record.size()-1));
				int previousDayCases = Integer.parseInt(record.get(record.size()-2));
				locationStats.setLastestTotalCases(lastestCases);
				locationStats.setDiffFromPreviousStats(lastestCases-previousDayCases);
				newStats.add(locationStats);
			}
			allStats = newStats;
			    
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
