package at.rs.alexa.me;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class ResponseHelper {

    private static final String DEFAULT_CARD_TITLE = "Meister Eder";

    public static SpeechletResponse createSpeechletResponse(String speechText, String repromptText,
                                                            boolean waitForMore) {
        SimpleCard card = createDefaultCard(speechText);
        return createPlainTextOutput(speechText, repromptText, waitForMore, card);
    }

    private static SpeechletResponse createPlainTextOutput(String speechText, String repromptText, boolean waitForMore, SimpleCard card) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (waitForMore) {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);
            return SpeechletResponse.newAskResponse(speech, reprompt, card);
        } else {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }

    private static SimpleCard createDefaultCard(String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle(DEFAULT_CARD_TITLE);
        card.setContent(speechText);
        return card;
    }
}
