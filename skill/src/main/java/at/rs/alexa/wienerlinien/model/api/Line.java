
package at.rs.alexa.wienerlinien.model.api;

import java.util.HashMap;
import java.util.Map;

public class Line {

    private String name;
    private String towards;
    private String direction;
    private String platform;
    private String richtungsId;
    private Boolean barrierFree;
    private Boolean realtimeSupported;
    private Boolean trafficjam;
    private Departures departures;
    private String type;
    private Integer lineId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRichtungsId() {
        return richtungsId;
    }

    public void setRichtungsId(String richtungsId) {
        this.richtungsId = richtungsId;
    }

    public Boolean getBarrierFree() {
        return barrierFree;
    }

    public void setBarrierFree(Boolean barrierFree) {
        this.barrierFree = barrierFree;
    }

    public Boolean getRealtimeSupported() {
        return realtimeSupported;
    }

    public void setRealtimeSupported(Boolean realtimeSupported) {
        this.realtimeSupported = realtimeSupported;
    }

    public Boolean getTrafficjam() {
        return trafficjam;
    }

    public void setTrafficjam(Boolean trafficjam) {
        this.trafficjam = trafficjam;
    }

    public Departures getDepartures() {
        return departures;
    }

    public void setDepartures(Departures departures) {
        this.departures = departures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
