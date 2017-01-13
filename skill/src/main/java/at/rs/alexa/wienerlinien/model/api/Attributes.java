
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.Map;


public class Attributes {

    private Integer rbl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getRbl() {
        return rbl;
    }

    public void setRbl(Integer rbl) {
        this.rbl = rbl;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
