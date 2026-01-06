package data;

import model.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionFileRepository {

    private String filePath;

    public PrescriptionFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Prescription> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Prescription> prescriptions = new ArrayList<>();

        for (String[] r : rows) {
            prescriptions.add(new Prescription(
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
                    r[11].trim(),
                    r[12].trim(),
                    r[13].trim(),
                    r[14].trim()
            ));
        }

        return prescriptions;
    }
}
