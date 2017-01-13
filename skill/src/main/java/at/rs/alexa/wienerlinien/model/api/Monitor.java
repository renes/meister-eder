
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monitor {

    private LocationStop locationStop;
    private List<Line> lines = null;
    private Attributes_ attributes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public LocationStop getLocationStop() {
        return locationStop;
    }

    public void setLocationStop(LocationStop locationStop) {
        this.locationStop = locationStop;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Attributes_ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes_ attributes) {
        this.attributes = attributes;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
