package at.rs.alexa.me.opendata.model;

public class PopulationData {

    String cityName;

    Integer population;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getPopulation() {
        return population;
    }

}
