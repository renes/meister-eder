package at.rs.alexa.me.opendata.services.population.populationdataprovider;

import at.rs.alexa.me.opendata.model.PopulationData;

import java.util.List;

public interface PopulationDataProvider {
    List<PopulationData> getPopulationData();
}
