package view;

import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientsPanel extends JPanel {

    private DefaultTableModel model;

    public PatientsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "First", "Last", "DOB", "NHS", "Phone", "Email", "GP Surgery"},
                0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showPatients(List<Patient> patients) {
        model.setRowCount(0);

        for (Patient p : patients) {
            model.addRow(new Object[]{
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getPhoneNumber(),
                    p.getEmail(),
                    p.getGpSurgeryId()
            });
        }
    }
}
