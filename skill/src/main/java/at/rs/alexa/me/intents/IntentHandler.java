package at.rs.alexa.me.intents;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

public interface IntentHandler {
    SpeechletResponse handleIntent(final IntentRequest request, final Session session);
}
