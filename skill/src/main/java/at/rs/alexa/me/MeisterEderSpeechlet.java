package at.rs.alexa.me;


import at.rs.alexa.me.intents.IntentHandler;
import at.rs.alexa.me.intents.MonitorIntent;
import at.rs.alexa.me.intents.PeopleCountIntent;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class MeisterEderSpeechlet implements Speechlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeisterEderSpeechlet.class);

    private final HashMap<String, IntentHandler> INTENTS = new HashMap<>();

    public MeisterEderSpeechlet() {
        INTENTS.put("MonitorIntent", new MonitorIntent());
        INTENTS.put("PeopleCountIntent", new PeopleCountIntent());
    }

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        LOGGER.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        LOGGER.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {

        LOGGER.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        IntentHandler intentHandler = INTENTS.get(intent.getName());

        if (intentHandler == null) {
            throw new SpeechletException("Invalid Intent");
        }

        return INTENTS.get(intent.getName()).handleIntent(request, session);

    }


    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    private SpeechletResponse getWelcomeResponse() {
        // Create the welcome message.
        String speechText =
                "Welcome to the Alexa Skills Kit sample. Please tell me your favorite color by "
                        + "saying, my favorite color is red";
        String repromptText =
                "Please tell me your favorite color by saying, my favorite color is red";

        return ResponseHelper.createSpeechletResponse(speechText, repromptText, true);
    }


}
