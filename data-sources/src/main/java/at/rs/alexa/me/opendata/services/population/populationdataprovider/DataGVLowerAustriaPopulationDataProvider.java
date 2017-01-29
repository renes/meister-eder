package at.rs.alexa.me.opendata.services.population.populationdataprovider;


import at.rs.alexa.me.opendata.model.PopulationData;
import at.rs.alexa.me.opendata.services.population.PopulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataGVLowerAustriaPopulationDataProvider implements PopulationDataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulationService.class);

    private static final String LOWER_AUSTRIA_DATA_URL = "http://open-data.noe.gv.at/RU2/noe_pop_1869-2011_lau2.csv";

    @Override
    public List<PopulationData> getPopulationData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> rawData = restTemplate.getForEntity(LOWER_AUSTRIA_DATA_URL, String.class);

        String[] lines = rawData.getBody().split("\r\n");

        LOGGER.info(this.getClass() + " + got " + lines.length + " data points");
        List<String> popData = Collections.unmodifiableList(Arrays.asList(lines));

        return popData.stream().map(s -> {

                    String[] result = s.split(";");
                    try {
                        if (result.length >= 4) {
                            PopulationData populationData = new PopulationData();
                            populationData.setCityName(result[4]);
                            populationData.setPopulation(Integer.valueOf(result[6]));
                            return populationData;
                        } else {
                            return null;
                        }
                    } catch (Exception ex) {
                        return null;
                    }
                }
        ).filter(Objects::nonNull).collect(Collectors.toList());

    }
}
