package data;

import model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentFileRepository {

    private String filePath;

    public AppointmentFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Appointment> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Appointment> appointments = new ArrayList<>();

        for (String[] r : rows) {
            appointments.add(new Appointment(
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
                    r[12].trim()
            ));
        }

        return appointments;
    }
}
