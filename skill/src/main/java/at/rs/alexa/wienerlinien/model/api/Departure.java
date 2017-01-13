
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.Map;

public class Departure {

    private DepartureTime departureTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DepartureTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DepartureTime departureTime) {
        this.departureTime = departureTime;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
