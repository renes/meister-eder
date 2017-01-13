
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data_ {

    private List<Monitor> monitors = null;
    private List<TrafficInfoCategory> trafficInfoCategories = null;
    private List<TrafficInfoCategoryGroup> trafficInfoCategoryGroups = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    public List<TrafficInfoCategory> getTrafficInfoCategories() {
        return trafficInfoCategories;
    }

    public void setTrafficInfoCategories(List<TrafficInfoCategory> trafficInfoCategories) {
        this.trafficInfoCategories = trafficInfoCategories;
    }

    public List<TrafficInfoCategoryGroup> getTrafficInfoCategoryGroups() {
        return trafficInfoCategoryGroups;
    }

    public void setTrafficInfoCategoryGroups(List<TrafficInfoCategoryGroup> trafficInfoCategoryGroups) {
        this.trafficInfoCategoryGroups = trafficInfoCategoryGroups;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
