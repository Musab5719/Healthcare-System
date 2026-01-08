package model;

import data.CsvUtil;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrescriptionManager {

    public Prescription createPrescription(String prescriptionsCsvPath,
                                           String patientId,
                                           String clinicianId,
                                           String appointmentId,
                                           String medicationName,
                                           String dosage,
                                           String frequency,
                                           String durationDays,
                                           String conditionTreated,
                                           String instructions,
                                           String pharmacyName,
                                           String status) {

        String id = "P" + System.currentTimeMillis();
        String today = LocalDate.now().toString();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Prescription p = new Prescription(
                id,
                patientId,
                clinicianId,
                appointmentId == null ? "" : appointmentId,
                today,
                medicationName,
                dosage,
                frequency,
                durationDays,
                conditionTreated,
                instructions,
                pharmacyName,
                status == null || status.isEmpty() ? "Issued" : status,
                now,
                ""
        );

        String csvLine = String.join(",",
                p.getPrescriptionId(),
                p.getPatientId(),
                p.getClinicianId(),
                p.getAppointmentId(),
                p.getPrescriptionDate(),
                safe(p.getMedicationName()),
                safe(p.getDosage()),
                safe(p.getFrequency()),
                safe(p.getDurationDays()),
                safe(p.getConditionTreated()),
                safe(p.getInstructions()),
                safe(p.getPharmacyName()),
                safe(p.getStatus()),
                p.getIssueDate(),
                p.getCollectionDate()
        );

        CsvUtil.appendLine(prescriptionsCsvPath, csvLine);
        writePrescriptionTextFile(p);

        return p;
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " ");
    }

    private void writePrescriptionTextFile(Prescription p) {
        try {
            File dir = new File("outputs");
            if (!dir.exists()) dir.mkdirs();

            File out = new File(dir, "prescription_" + p.getPrescriptionId() + ".txt");

            try (FileWriter fw = new FileWriter(out)) {
                fw.write("PRESCRIPTION\n");
                fw.write("Prescription ID: " + p.getPrescriptionId() + "\n");
                fw.write("Prescription Date: " + p.getPrescriptionDate() + "\n\n");

                fw.write("Patient ID: " + p.getPatientId() + "\n");
                fw.write("Clinician ID: " + p.getClinicianId() + "\n");
                fw.write("Appointment ID: " + p.getAppointmentId() + "\n\n");

                fw.write("Medication: " + p.getMedicationName() + "\n");
                fw.write("Dosage: " + p.getDosage() + "\n");
                fw.write("Frequency: " + p.getFrequency() + "\n");
                fw.write("Duration (days): " + p.getDurationDays() + "\n");
                fw.write("Condition treated: " + p.getConditionTreated() + "\n\n");

                fw.write("Instructions:\n" + p.getInstructions() + "\n\n");

                fw.write("Pharmacy: " + p.getPharmacyName() + "\n");
                fw.write("Status: " + p.getStatus() + "\n");
                fw.write("Issue date: " + p.getIssueDate() + "\n");
                fw.write("Collection date: " + p.getCollectionDate() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
