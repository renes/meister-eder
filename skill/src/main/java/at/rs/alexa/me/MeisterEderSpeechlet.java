package at.rs.alexa.me;


import at.rs.alexa.me.intents.IntentHandler;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeisterEderSpeechlet implements Speechlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeisterEderSpeechlet.class);

    @Autowired
    private List<IntentHandler> intentHandlers;

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
        Optional<IntentHandler> intentHandler = intentHandlers.stream().filter(currentHandler -> currentHandler.getIntentName().equals(intent.getName())).findFirst();

        if (!intentHandler.isPresent()) {
            throw new SpeechletException("Invalid Intent");
        }

        return intentHandler.get().handleIntent(request, session);
    }


    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        LOGGER.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    private SpeechletResponse getWelcomeResponse() {

        String speechText = "Welcome to the Meister Eder";
        String repromptText = "Welcome to the Meister Eder";

        return ResponseHelper.createSpeechletResponse(speechText, repromptText, true);
    }
}
