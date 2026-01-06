package view;

import model.Facility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FacilitiesPanel extends JPanel {

    private DefaultTableModel model;

    public FacilitiesPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Type", "Postcode", "Phone", "Email", "Manager", "Capacity"},
                0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showFacilities(List<Facility> facilities) {
        model.setRowCount(0);

        for (Facility f : facilities) {
            model.addRow(new Object[]{
                    f.getFacilityId(),
                    f.getFacilityName(),
                    f.getFacilityType(),
                    f.getPostcode(),
                    f.getPhoneNumber(),
                    f.getEmail(),
                    f.getManagerName(),
                    f.getCapacity()
            });
        }
    }
}
