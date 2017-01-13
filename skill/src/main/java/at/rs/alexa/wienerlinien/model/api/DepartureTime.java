
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.Map;

public class DepartureTime {

    private String timePlanned;
    private String timeReal;
    private Integer countdown;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTimePlanned() {
        return timePlanned;
    }

    public void setTimePlanned(String timePlanned) {
        this.timePlanned = timePlanned;
    }

    public String getTimeReal() {
        return timeReal;
    }

    public void setTimeReal(String timeReal) {
        this.timeReal = timeReal;
    }

    public Integer getCountdown() {
        return countdown;
    }

    public void setCountdown(Integer countdown) {
        this.countdown = countdown;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
