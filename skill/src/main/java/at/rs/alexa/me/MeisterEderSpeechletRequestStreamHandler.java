package at.rs.alexa.me;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;

public class MeisterEderSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    public MeisterEderSpeechletRequestStreamHandler() {
        super(new MeisterEderSpeechlet(), new HashSet<>());
    }
}
