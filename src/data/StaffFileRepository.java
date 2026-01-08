package data;

import model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffFileRepository {

    private String filePath;

    public StaffFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Staff> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Staff> staffList = new ArrayList<>();

        for (String[] r : rows) {
            staffList.add(new Staff(
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

        return staffList;
    }
}
