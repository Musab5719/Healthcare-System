package model;

import data.CsvUtil;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReferralManager {

    private static ReferralManager instance;

    private ReferralManager() {}

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public Referral createReferral(String referralsCsvPath,
                                   String patientId,
                                   String referringClinicianId,
                                   String referredToClinicianId,
                                   String referringFacilityId,
                                   String referredToFacilityId,
                                   String urgencyLevel,
                                   String reasonForReferral,
                                   String clinicalSummary,
                                   String instructions,
                                   String appointmentId) {

        String id = "R" + System.currentTimeMillis();
        String today = LocalDate.now().toString();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Referral r = new Referral(
                id,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                today,
                urgencyLevel,
                reasonForReferral,
                clinicalSummary,
                instructions,
                "Created",
                appointmentId == null ? "" : appointmentId,
                "",
                now,
                now
        );

        String csvLine = String.join(",",
                r.getReferralId(),
                r.getPatientId(),
                r.getReferringClinicianId(),
                r.getReferredToClinicianId(),
                r.getReferringFacilityId(),
                r.getReferredToFacilityId(),
                r.getReferralDate(),
                r.getUrgencyLevel(),
                safe(r.getReasonForReferral()),
                safe(r.getClinicalSummary()),
                safe(r.getInstructions()),
                r.getStatus(),
                r.getAppointmentId(),
                r.getNotes(),
                r.getCreatedDate(),
                r.getLastUpdated()
        );

        CsvUtil.appendLine(referralsCsvPath, csvLine);

        writeReferralTextFile(r);

        return r;
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " ");
    }

    private void writeReferralTextFile(Referral r) {
        try {
            File dir = new File("outputs");
            if (!dir.exists()) dir.mkdirs();

            File out = new File(dir, "referral_" + r.getReferralId() + ".txt");

            try (FileWriter fw = new FileWriter(out)) {
                fw.write("REFERRAL\n");
                fw.write("Referral ID: " + r.getReferralId() + "\n");
                fw.write("Date: " + r.getReferralDate() + "\n");
                fw.write("Urgency: " + r.getUrgencyLevel() + "\n\n");

                fw.write("Patient ID: " + r.getPatientId() + "\n");
                fw.write("Referring Clinician ID: " + r.getReferringClinicianId() + "\n");
                fw.write("Referred To Clinician ID: " + r.getReferredToClinicianId() + "\n");
                fw.write("Referring Facility ID: " + r.getReferringFacilityId() + "\n");
                fw.write("Referred To Facility ID: " + r.getReferredToFacilityId() + "\n");
                fw.write("Appointment ID: " + r.getAppointmentId() + "\n\n");

                fw.write("Reason:\n" + r.getReasonForReferral() + "\n\n");
                fw.write("Clinical Summary:\n" + r.getClinicalSummary() + "\n\n");
                fw.write("Instructions:\n" + r.getInstructions() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
