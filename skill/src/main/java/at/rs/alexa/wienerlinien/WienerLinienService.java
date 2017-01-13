package at.rs.alexa.wienerlinien;

import at.rs.alexa.sisyphus.Task;
import at.rs.alexa.sisyphus.Worker;
import at.rs.alexa.wienerlinien.model.api.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WienerLinienService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WienerLinienService.class);

    private static final String URI = "http://www.wienerlinien.at/ogd_realtime/monitor?rbl=4938&activateTrafficInfo=stoerungkurz&activateTrafficInfo=stoerunglang&activateTrafficInfo=aufzugsinfo&sender=";

    private static final String WIENER_LINIEN_KEY = "WIENER_LINIEN_KEY";

    private final Worker<List<Integer>> worker = new Worker<>();

    public List<Integer> getNextDepartures() {

        Task<List<Integer>> listTask = () -> {

            List<Integer> departuresInXMinutes = new ArrayList<>();
            RestTemplate restTemplate = new RestTemplate();
            Data data = restTemplate.getForObject(URI +  System.getenv(WIENER_LINIEN_KEY), Data.class);

            if (data.getData().getMonitors().get(0).getLines().get(0).getDepartures().getDeparture().get(0).getDepartureTime().getCountdown() == null) {
                return departuresInXMinutes;
            }

            int currentSubwayIn = data.getData().getMonitors().get(0).getLines().get(0).getDepartures().getDeparture().get(0).getDepartureTime().getCountdown();
            int nextSubwayIn = data.getData().getMonitors().get(0).getLines().get(0).getDepartures().getDeparture().get(1).getDepartureTime().getCountdown();

            departuresInXMinutes.add(currentSubwayIn);
            departuresInXMinutes.add(nextSubwayIn);

            return departuresInXMinutes;
        };



        Optional<List<Integer>> result = worker.execute(listTask);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new WienerLinienServiceException("Could not load data from wiener linien.");
        }


    }

}
