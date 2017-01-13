
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departures {

    private List<Departure> departure = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Departure> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Departure> departure) {
        this.departure = departure;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
