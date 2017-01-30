package at.rs.alexa.me.intents;


import at.rs.alexa.me.MeisterEderSpeechlet;
import at.rs.alexa.me.ResponseHelper;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class PeopleCountIntent implements IntentHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MeisterEderSpeechlet.class);

    @Value(value = "classpath:cities.csv")
    private Resource csvFile;

    @Override
    public SpeechletResponse handleIntent(IntentRequest request, Session session) {
        Intent intent = request.getIntent();

        try {
            Map<String, Slot> slots = intent.getSlots();

            Slot citySlot = slots.get("City");

            if (citySlot == null) {
                return ResponseHelper.createSpeechletResponse("Diese Stadt kenne ich leider nicht", "", false);
            }

            String city = citySlot.getValue().replace(".", "").replace(" ", "").toLowerCase();
            Optional<Integer> count = IOUtils.readLines(csvFile.getInputStream()).stream().map(String::toLowerCase).map(s -> s.replace(".", "").replace(" ", "")).filter(s -> s.startsWith(city)).findFirst().map(s -> Integer.valueOf(s.split(";")[1]));

            if (count.isPresent()) {
                return ResponseHelper.createSpeechletResponse("In " + citySlot.getValue() + " wohnen " + count.get() + " personen", "", false);
            } else {
                return ResponseHelper.createSpeechletResponse("Sorry die Stadt " + citySlot.getValue() + " kenne ich nicht", "", false);

            }

        } catch (IOException e) {
            LOGGER.error("Could not open file", e);
            return ResponseHelper.createSpeechletResponse("Leider konnten wir zur Zeit nicht auf die nötigen Daten zugreifen. Bitte versuche es später nochmal", "", false);
        }
    }

    @Override
    public String getIntentName() {
        return "PeopleCountIntent";
    }
}
