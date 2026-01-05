package data;

import model.Clinician;

import java.util.ArrayList;
import java.util.List;

public class ClinicianFileRepository {

    private String filePath;

    public ClinicianFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Clinician> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Clinician> clinicians = new ArrayList<>();

        for (String[] r : rows) {
            clinicians.add(new Clinician(
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
                    r[10].trim(),
                    r[11].trim()
            ));
        }

        return clinicians;
    }
}
