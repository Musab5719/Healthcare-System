package view;

import model.Clinician;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CliniciansPanel extends JPanel {

    private DefaultTableModel model;

    public CliniciansPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Title", "Speciality", "GMC", "Phone", "Email", "Workplace", "Type", "Status"},
                0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showClinicians(List<Clinician> clinicians) {
        model.setRowCount(0);

        for (Clinician c : clinicians) {
            model.addRow(new Object[]{
                    c.getClinicianId(),
                    c.getFirstName() + " " + c.getLastName(),
                    c.getTitle(),
                    c.getSpeciality(),
                    c.getGmcNumber(),
                    c.getPhoneNumber(),
                    c.getEmail(),
                    c.getWorkplaceId(),
                    c.getWorkplaceType(),
                    c.getEmploymentStatus()
            });
        }
    }
}
