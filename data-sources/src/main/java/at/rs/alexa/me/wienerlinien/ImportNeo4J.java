package at.rs.alexa.me.wienerlinien;

import org.neo4j.driver.v1.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * Created by fuchsst on 14.01.17.
 */
public class ImportNeo4J {

    private class Stop implements Comparable<Stop> {
        Long id;
        Long lineId;
        Long stationId;
        String direction;
        Integer order;
        String rbl;
        String area;
        String name;
        String lat;
        String lon;

        public Stop(String row) {
            String[] fields = row.split(";");
            id = Long.parseLong(fields[0]);
            lineId = Long.parseLong(fields[1]);
            stationId = Long.parseLong(fields[2]);
            direction = fields[3].replace("\"", "");
            order = Integer.parseInt(fields[4]);
            rbl = fields[5].replace("\"", "");
            area = fields[6];
            name = fields[7].replace("\"", "");
            lat = fields[8];
            lon = fields[9];
        }


        @Override
        public String toString() {
            return "Stop{" +
                    "id='" + id + '\'' +
                    ", lineId='" + lineId + '\'' +
                    ", stationId='" + stationId + '\'' +
                    ", direction='" + direction + '\'' +
                    ", order='" + order + '\'' +
                    ", rbl='" + rbl + '\'' +
                    ", area='" + area + '\'' +
                    ", name='" + name + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Stop stop) {
            int lineSort = lineId.compareTo(stop.lineId) < 0 ? -100 : (lineId.compareTo(stop.lineId) == 0 ? 0 : 100);
            int directionSort = direction.compareTo(stop.direction) < 0 ? -10 : (direction.compareTo(stop.direction) == 0 ? 0 : 10);
            int orderSort = order.compareTo(stop.order) < 0 ? -1 : (order.compareTo(stop.order) == 0 ? 0 : 1);
            int totalSort = lineSort + directionSort + orderSort;

            return totalSort;
        }
    }

    public static void main(final String[] args) throws Exception {
        ImportNeo4J testData = new ImportNeo4J();
        testData.execute();
    }

    public void execute() {
        final List<String> stationsRows = readFile("/home/fuchsst/IdeaProjects/meister-eder/data-sources/data/wienerlinien/wienerlinien-ogd-haltestellen.csv");
        final List<String> linesRows = readFile("/home/fuchsst/IdeaProjects/meister-eder/data-sources/data/wienerlinien/wienerlinien-ogd-linien.csv");
        final List<String> stopsRows = readFile("/home/fuchsst/IdeaProjects/meister-eder/data-sources/data/wienerlinien/wienerlinien-ogd-steige.csv");

        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
        Session session = driver.session();

        stationsRows.forEach(row -> {
            System.out.println(row);
            String[] fields = row.split(";");
            if (!fields[0].startsWith("\"")) {
                session.run("MERGE (B" + fields[0] + ":Station {id: {id}, name: {name}, diva: {diva}, lat: {lat}, long: {long}})",
                        parameters("id", Integer.parseInt(fields[0]), "name", fields[3].replace("\"", ""), "diva", fields[2], "lat", fields[6], "long", fields[7]));

            }
        });
        session.run("CREATE INDEX ON :Station(id)");
        session.run("CREATE INDEX ON :Station(name)");


        linesRows.forEach(row -> {
            System.out.println(row);
            String[] fields = row.split(";");
            if (!fields[0].startsWith("\"")) {
                session.run("MERGE (L" + fields[0] + ":Line {id: {id}, name: {name}, realtime: {realtime}, type: {type}})",
                        parameters("id", Integer.parseInt(fields[0]), "name", fields[1].replace("\"", ""), "realtime", fields[3], "type", fields[4].replace("\"", "")));
            }
        });
        session.run("CREATE INDEX ON :Line(id)");
        session.run("CREATE INDEX ON :Line(name)");

        //sort list to connect the stops of a line
        List<Stop> stops = stopsRows
                .stream()
                .filter(row -> !row.startsWith("\"") /*not Header*/)
                .map(row -> new Stop(row))
                .sorted()
                .collect(Collectors.toList());

        Long currentLine = -1L;
        String currentDirection = "";
        Long previousStop = -1L;
        Long previousStation = -1L;
        for (Stop stop : stops) {
            System.out.println(stop);

            session.run("MERGE (S" + stop.id + ":Stop {id: {id}, name: {name}, direction: {direction}, rbl: {rbl}, area: {area}, order: {order}, lat: {lat}, long: {long}})",
                    parameters("id", stop.id, "direction", stop.direction, "order", stop.order, "rbl", stop.rbl, "area", stop.area, "name", stop.name, "lat", stop.lat, "long", stop.lon));

            session.run("MATCH (l:Line {id:{lineid}}), (s:Stop {id:{stopid}}) MERGE (l)-[:STOPS_AT]->(s)", parameters("lineid", stop.lineId, "stopid", stop.id));
            session.run("MATCH (b:Station {id:{stationid}}), (s:Stop {id:{stopid}}) MERGE (b)-[:HAS_STOP]->(s)", parameters("stationid", stop.stationId, "stopid", stop.id));

            // link all stops at the same station
            stops.stream()
                    .filter(intersection -> !intersection.lineId.equals(stop.lineId) && intersection.stationId.equals(stop.stationId))
                    .forEach(intersection -> {
                        session.run("MATCH (s1:Stop {id:{stopId1}}), (s2:Stop {id:{stopId2}}) MERGE (s1)-[:INTERSECTS_WITH]->(s2)", parameters("stopId1", stop.id, "stopId2", intersection.id));
                        session.run("MATCH (s1:Stop {id:{stopId1}}), (s2:Stop {id:{stopId2}}) MERGE (s1)-[:INTERSECTS_WITH]->(s2)", parameters("stopId1", intersection.id, "stopId2", stop.id));
                    });

            // link stop to the destination station
            Stop lastStopOfLine = stops.stream()
                    .filter(otherStop -> otherStop.lineId.equals(stop.lineId) && otherStop.direction.equals(stop.direction))
                    .reduce((stop1, stop2) -> stop1.order>stop2.order?stop1:stop2)
                    .get();
            if (lastStopOfLine != null && !lastStopOfLine.order.equals(stop.order)){
                session.run("MATCH (s:Stop {id:{stopid}}), (dest:Station {id:{stationid}}) MERGE (s)-[:GOES_TO]->(dest)", parameters("stopid", stop.id, "stationid", lastStopOfLine.stationId));
            }

            // link connected stations and stops
            if (currentDirection.equals(stop.direction) && currentLine.equals(stop.lineId)) {
                session.run("MATCH (s1:Stop {id:{prevStop}}), (s2:Stop {id:{stopid}}) MERGE (s1)-[:NEXT_STOP]->(s2)", parameters("prevStop", previousStop, "stopid", stop.id));
                    session.run("MATCH (s1:Station {id:{prevStation}}), (s2:Station {id:{stationid}}) MERGE (s1)-[:NEXT_STATION]->(s2)", parameters("prevStation", previousStation, "stationid", stop.stationId));
            } else {
                currentDirection = stop.direction;
                currentLine = stop.lineId;
            }
            previousStop = stop.id;
            previousStation=stop.stationId;
        }
        session.run("CREATE INDEX ON :Stop(id)");
        session.run("CREATE INDEX ON :Stop(name)");

        session.close();
        driver.close();
    }

    private List<String> readFile(String path) {
        List<String> rows = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
