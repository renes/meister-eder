package at.rs.alexa.me.intents;


import at.rs.alexa.me.ResponseHelper;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * see
 * https://github.com/xkonni/raspberry-remote
 *
 * how to install raspberry remote and connect
 * it to your power plug
 */
@Service
public class PowerPlugIntent implements IntentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerPlugIntent.class);

    private static final String RASPI_REMOTE_HOME = "/home/pi/raspberry-remote/send";

    private static final String SIGNAL_OFF = "0";
    private static final String SIGNAL_ON = "1";

    private static final String REMOTE_CONTROL_CODE = "111111";
    private static final String POWER_PLUG_CODE = "3";

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public SpeechletResponse handleIntent(IntentRequest request, Session session) {
        Slot slot = request.getIntent().getSlot("Action");

        if (slot.getValue().startsWith("aus")) {
            sendSignal(SIGNAL_OFF);
            return ResponseHelper.createSpeechletResponse("Steckdose wurde ausgeschalten", "", false);
        } else {
            sendSignal(SIGNAL_ON);
            return ResponseHelper.createSpeechletResponse("Steckdose wurde eingeschalten", "", false);
        }

    }

    public void sendSignal(String signalParameter) {

        executor.submit(() -> {
            for (int i  = 0; i <= 10; i++) {
                ProcessBuilder pb = new ProcessBuilder(RASPI_REMOTE_HOME, REMOTE_CONTROL_CODE, POWER_PLUG_CODE, signalParameter);
                pb.directory(new File("/"));

                Process process = null;
                try {
                    process = pb.start();
                } catch (Exception e) {
                    LOGGER.error("IO Error sending signal", e);
                }

                if (process != null) {
                    LOGGER.info("Exit value of sending process: " + process.exitValue());
                }
            }
        });
    }

    @Override
    public String getIntentName() {
        return "PowerPlugIntent";
    }
}
