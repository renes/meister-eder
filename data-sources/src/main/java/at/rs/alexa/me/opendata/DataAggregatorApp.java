package at.rs.alexa.me.opendata;


import at.rs.alexa.me.opendata.services.population.PopulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class DataAggregatorApp {

    public static void main(String[] args) {
        PopulationService populationService = SpringApplication.run(DataAggregatorApp.class, args).getBean(PopulationService.class);
        populationService.collectData();
    }

}