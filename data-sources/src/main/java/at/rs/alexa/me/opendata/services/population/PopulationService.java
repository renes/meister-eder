package at.rs.alexa.me.opendata.services.population;

import at.rs.alexa.me.opendata.model.PopulationData;
import at.rs.alexa.me.opendata.services.population.populationdataprovider.PopulationDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulationService.class);

    private static final String OUTPUT_FILE = "out.csv";


    @Autowired
    private List<PopulationDataProvider> populationDataProviders;

    public void collectData() {

        List<PopulationData> result = new ArrayList<>();
        populationDataProviders.forEach(dataProvider -> result.addAll(dataProvider.getPopulationData()));

        Path path = Paths.get(OUTPUT_FILE);
        LOGGER.info("Writting to " + path.toAbsolutePath());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            result.forEach(data -> {
                try {
                    writer.write(data.getCityName() + ";" + data.getPopulation() + System.lineSeparator());
                } catch (IOException e) {
                    LOGGER.error("Failed to write output file", e);
                }
            });

        } catch (IOException e) {
           LOGGER.error("Failed to write output file", e);
        }

        LOGGER.info("Finished writting to "+ path.toAbsolutePath());

    }

}
