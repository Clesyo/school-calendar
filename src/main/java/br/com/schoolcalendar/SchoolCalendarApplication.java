package br.com.schoolcalendar;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.schoolcalendar.seeders.DefaultSeeder;

@SpringBootApplication
public class SchoolCalendarApplication {

	@Autowired
	private SchoolCalendarApiContext context;
	
	@Autowired
	private DefaultSeeder defaultSeeder;

	public static void main(String[] args) {
		SpringApplication.run(SchoolCalendarApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		context.load();
		defaultSeeder.seedProfiles();
		defaultSeeder.seedUser();
		defaultSeeder.seedStatesAndCities();
		defaultSeeder.seedDiscipline();
	}
}
