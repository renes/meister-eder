package at.rs.alexa.me.opendata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class TestData {

    public static void main(final String[] args) throws Exception {
        TestData testData = new TestData();
        testData.execute();
    }

    public void execute() {
        String path = "data/noe.csv";

        List<String> names = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                names.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> result2 = names.stream().map(s -> {

                    String[] result = s.split(";");
                    try {
                        if (result.length >= 4) {
                            Data data = new Data();
                            data.key = result[4];
                            data.count = Integer.valueOf(result[6]);
                            return data;
                        } else {
                            return null;
                        }
                    } catch (Exception ex) {
                        return null;
                    }
                }
        ).filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Data::getKey, Collectors.summingInt(Data::getCount)));

        result2.forEach((s, integer) -> System.out.println(s + ";" + integer));

    }

    private class Data {
        String key;

        Integer count;

        public String getKey() {
            return key;
        }

        public Integer getCount() {
            return count;
        }
    }
}
