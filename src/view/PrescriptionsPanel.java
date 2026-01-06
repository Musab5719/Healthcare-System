package view;

import model.Prescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrescriptionsPanel extends JPanel {

    private DefaultTableModel model;

    public PrescriptionsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient", "Clinician", "Medication", "Dosage", "Frequency", "Status", "Pharmacy"},
                0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showPrescriptions(List<Prescription> prescriptions) {
        model.setRowCount(0);

        for (Prescription p : prescriptions) {
            model.addRow(new Object[]{
                    p.getPrescriptionId(),
                    p.getPatientId(),
                    p.getClinicianId(),
                    p.getMedicationName(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getStatus(),
                    p.getPharmacyName()
            });
        }
    }
}
