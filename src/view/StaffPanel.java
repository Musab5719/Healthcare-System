package view;

import model.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffPanel extends JPanel {

    private DefaultTableModel model;

    public StaffPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Role", "Department", "Facility", "Phone", "Email", "Status", "Access"},
                0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showStaff(List<Staff> staffList) {
        model.setRowCount(0);

        for (Staff s : staffList) {
            model.addRow(new Object[]{
                    s.getStaffId(),
                    s.getFirstName() + " " + s.getLastName(),
                    s.getRole(),
                    s.getDepartment(),
                    s.getFacilityId(),
                    s.getPhoneNumber(),
                    s.getEmail(),
                    s.getEmploymentStatus(),
                    s.getAccessLevel()
            });
        }
    }
}
