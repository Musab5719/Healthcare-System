package view;

import controller.PrescriptionController;

import javax.swing.*;
import java.awt.*;

public class CreatePrescriptionDialog extends JDialog {

    public CreatePrescriptionDialog(PrescriptionController controller) {
        setTitle("Create Prescription");
        setSize(500, 560);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(0, 1));

        JTextField patientId = new JTextField();
        JTextField clinicianId = new JTextField();
        JTextField appointmentId = new JTextField();

        JTextField medicationName = new JTextField();
        JTextField dosage = new JTextField();
        JTextField frequency = new JTextField();
        JTextField durationDays = new JTextField();
        JTextField conditionTreated = new JTextField();
        JTextField pharmacyName = new JTextField();
        JTextField status = new JTextField();

        JTextArea instructions = new JTextArea(4, 20);

        add(row("Patient ID", patientId));
        add(row("Clinician ID", clinicianId));
        add(row("Appointment ID (optional)", appointmentId));

        add(row("Medication name", medicationName));
        add(row("Dosage", dosage));
        add(row("Frequency", frequency));
        add(row("Duration days", durationDays));
        add(row("Condition treated", conditionTreated));
        add(areaRow("Instructions", instructions));
        add(row("Pharmacy name", pharmacyName));
        add(row("Status (optional)", status));

        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(save);

        add(buttons);

        cancel.addActionListener(e -> dispose());

        save.addActionListener(e -> {
            if (patientId.getText().trim().isEmpty()) return;

            controller.createPrescription(
                    patientId.getText().trim(),
                    clinicianId.getText().trim(),
                    appointmentId.getText().trim(),
                    medicationName.getText().trim(),
                    dosage.getText().trim(),
                    frequency.getText().trim(),
                    durationDays.getText().trim(),
                    conditionTreated.getText().trim(),
                    instructions.getText().trim(),
                    pharmacyName.getText().trim(),
                    status.getText().trim()
            );

            dispose();
        });
    }

    private JPanel row(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private JPanel areaRow(String label, JTextArea area) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(new JScrollPane(area), BorderLayout.CENTER);
        return p;
    }
}
