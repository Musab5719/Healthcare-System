package view;

import controller.ReferralController;

import javax.swing.*;
import java.awt.*;

public class CreateReferralDialog extends JDialog {

    public CreateReferralDialog(ReferralController controller) {
        setTitle("Create Referral");
        setSize(500, 520);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(0, 1));

        JTextField patientId = new JTextField();
        JTextField fromClinicianId = new JTextField();
        JTextField toClinicianId = new JTextField();
        JTextField fromFacilityId = new JTextField();
        JTextField toFacilityId = new JTextField();
        JTextField urgency = new JTextField();
        JTextField appointmentId = new JTextField();

        JTextArea reason = new JTextArea(3, 20);
        JTextArea summary = new JTextArea(4, 20);
        JTextArea instructions = new JTextArea(3, 20);

        add(row("Patient ID", patientId));
        add(row("Referring Clinician ID", fromClinicianId));
        add(row("Referred To Clinician ID", toClinicianId));
        add(row("Referring Facility ID", fromFacilityId));
        add(row("Referred To Facility ID", toFacilityId));
        add(row("Urgency Level (Low/Medium/High)", urgency));
        add(row("Appointment ID (optional)", appointmentId));

        add(areaRow("Reason for Referral", reason));
        add(areaRow("Clinical Summary", summary));
        add(areaRow("Instructions", instructions));

        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(save);

        add(buttons);

        cancel.addActionListener(e -> dispose());

        save.addActionListener(e -> {
            if (patientId.getText().trim().isEmpty()) return;

            controller.createReferral(
                    patientId.getText().trim(),
                    fromClinicianId.getText().trim(),
                    toClinicianId.getText().trim(),
                    fromFacilityId.getText().trim(),
                    toFacilityId.getText().trim(),
                    urgency.getText().trim(),
                    reason.getText().trim(),
                    summary.getText().trim(),
                    instructions.getText().trim(),
                    appointmentId.getText().trim()
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
