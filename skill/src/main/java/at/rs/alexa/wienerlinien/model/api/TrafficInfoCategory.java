
package at.rs.alexa.wienerlinien.model.api;

import java.util.HashMap;
import java.util.Map;

public class TrafficInfoCategory {

    private Integer id;
    private Integer refTrafficInfoCategoryGroupId;
    private String name;
    private String trafficInfoNameList;
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefTrafficInfoCategoryGroupId() {
        return refTrafficInfoCategoryGroupId;
    }

    public void setRefTrafficInfoCategoryGroupId(Integer refTrafficInfoCategoryGroupId) {
        this.refTrafficInfoCategoryGroupId = refTrafficInfoCategoryGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrafficInfoNameList() {
        return trafficInfoNameList;
    }

    public void setTrafficInfoNameList(String trafficInfoNameList) {
        this.trafficInfoNameList = trafficInfoNameList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
