
package at.rs.alexa.wienerlinien.model.api;
import java.util.HashMap;
import java.util.Map;

public class Data {

    private Data_ data;
    private Message message;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
