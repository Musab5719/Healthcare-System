package data;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientFileRepository {

    private String filePath;

    public PatientFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Patient> loadAll() {
        List<String[]> rows = CsvUtil.readAll(filePath);
        List<Patient> patients = new ArrayList<>();

        for (String[] r : rows) {
            patients.add(new Patient(
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
                    r[13].trim()
            ));
        }

        return patients;
    }

    public void saveAll(List<Patient> patients) {
        String header = CsvUtil.readHeader(filePath);
        List<String> lines = new ArrayList<>();

        for (Patient p : patients) {
            String line = String.join(",",
                    p.getPatientId(),
                    safe(p.getFirstName()),
                    safe(p.getLastName()),
                    safe(p.getDateOfBirth()),
                    safe(p.getNhsNumber()),
                    safe(p.getGender()),
                    safe(p.getPhoneNumber()),
                    safe(p.getEmail()),
                    safe(p.getAddress()),
                    safe(p.getPostcode()),
                    safe(p.getEmergencyContactName()),
                    safe(p.getEmergencyContactPhone()),
                    safe(p.getRegistrationDate()),
                    safe(p.getGpSurgeryId())
            );
            lines.add(line);
        }

        CsvUtil.writeAll(filePath, header, lines);
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " ");
    }
}
