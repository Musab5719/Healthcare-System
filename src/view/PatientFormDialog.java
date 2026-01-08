package view;

import model.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientFormDialog extends JDialog {

    private Patient result;

    public PatientFormDialog(Patient existing) {
        setTitle(existing == null ? "Add Patient" : "Edit Patient");
        setSize(520, 650);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(0, 1));

        JTextField patientId = new JTextField();
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField dob = new JTextField();
        JTextField nhs = new JTextField();
        JTextField gender = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        JTextField address = new JTextField();
        JTextField postcode = new JTextField();
        JTextField emName = new JTextField();
        JTextField emPhone = new JTextField();
        JTextField regDate = new JTextField();
        JTextField gpSurgeryId = new JTextField();

        if (existing != null) {
            patientId.setText(existing.getPatientId());
            patientId.setEditable(false);
            firstName.setText(existing.getFirstName());
            lastName.setText(existing.getLastName());
            dob.setText(existing.getDateOfBirth());
            nhs.setText(existing.getNhsNumber());
            gender.setText(existing.getGender());
            phone.setText(existing.getPhoneNumber());
            email.setText(existing.getEmail());
            address.setText(existing.getAddress());
            postcode.setText(existing.getPostcode());
            emName.setText(existing.getEmergencyContactName());
            emPhone.setText(existing.getEmergencyContactPhone());
            regDate.setText(existing.getRegistrationDate());
            gpSurgeryId.setText(existing.getGpSurgeryId());
        }

        add(row("Patient ID", patientId));
        add(row("First name", firstName));
        add(row("Last name", lastName));
        add(row("Date of birth (YYYY-MM-DD)", dob));
        add(row("NHS number", nhs));
        add(row("Gender", gender));
        add(row("Phone", phone));
        add(row("Email", email));
        add(row("Address", address));
        add(row("Postcode", postcode));
        add(row("Emergency contact name", emName));
        add(row("Emergency contact phone", emPhone));
        add(row("Registration date (YYYY-MM-DD)", regDate));
        add(row("GP surgery ID", gpSurgeryId));

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(save);

        add(buttons);

        cancel.addActionListener(e -> {
            result = null;
            dispose();
        });

        save.addActionListener(e -> {
            if (patientId.getText().trim().isEmpty()) return;

            result = new Patient(
                    patientId.getText().trim(),
                    firstName.getText().trim(),
                    lastName.getText().trim(),
                    dob.getText().trim(),
                    nhs.getText().trim(),
                    gender.getText().trim(),
                    phone.getText().trim(),
                    email.getText().trim(),
                    address.getText().trim(),
                    postcode.getText().trim(),
                    emName.getText().trim(),
                    emPhone.getText().trim(),
                    regDate.getText().trim(),
                    gpSurgeryId.getText().trim()
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

    public Patient getResult() {
        return result;
    }
}
