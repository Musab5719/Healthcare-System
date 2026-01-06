package data;

import model.Facility;

import java.util.ArrayList;
import java.util.List;

public class FacilityFileRepository {

    private String filePath;

    public FacilityFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Facility> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Facility> facilities = new ArrayList<>();

        for (String[] r : rows) {
            facilities.add(new Facility(
                    r[0].trim(),
                    r[1].trim(),
                    r[2].trim(),
                    r[3].trim(),
                    r[4].trim(),
                    r[5].trim(),
                    r[6].trim(),
                    r[7].trim(),
                    r[8].trim(),
                    r[9].trim(),
                    r[10].trim()
            ));
        }

        return facilities;
    }
}
