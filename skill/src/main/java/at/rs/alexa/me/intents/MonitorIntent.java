package at.rs.alexa.me.intents;

import at.rs.alexa.me.ResponseHelper;
import at.rs.alexa.wienerlinien.WienerLinienService;
import at.rs.alexa.wienerlinien.WienerLinienServiceException;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

import java.util.List;


public class MonitorIntent implements IntentHandler {

    private WienerLinienService wienerLinienService = new WienerLinienService();

    @Override
    public SpeechletResponse handleIntent(IntentRequest request, Session session) {
        try {
            List<Integer> departures = wienerLinienService.getNextDepartures();

            if (departures.size() == 0) {
                return ResponseHelper.createSpeechletResponse("Es sind zur Zeit leider keine Echtzeitdaten verfügbar", "", false);
            } else if (departures.size() == 1) {
                int departureInMinutes = departures.get(0);
                return ResponseHelper.createSpeechletResponse("U3 fährt in " + departureInMinutes +  " " + getSingularOrPuralWording(departureInMinutes), "", false);
            } else {
                int departureInMinutes = departures.get(0);
                int departureInMinutesNext = departures.get(1);
                return ResponseHelper.createSpeechletResponse("U3 fährt in " + departureInMinutes + " " + getSingularOrPuralWording(departureInMinutes) + " beziehungsweise " + +departureInMinutesNext + " " + getSingularOrPuralWording(departureInMinutesNext) , "", false);
            }

        } catch (WienerLinienServiceException ex) {
            return ResponseHelper.createSpeechletResponse("Das Wiener Linien Service ist zur Zeit nicht verfügbar.", "", false);
            //TODO keep session open, Ask "Willst du es noch mal versuchen?" and retry it
        }
    }

    private String getSingularOrPuralWording(int departureInMinutes) {
        String minutes = "Minuten";
        if (departureInMinutes == 1) {
            minutes = "Minute";
        }

        return minutes;
    }

}
