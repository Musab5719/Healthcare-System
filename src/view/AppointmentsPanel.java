package view;

import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AppointmentsPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;

    public AppointmentsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient ID", "Clinician ID", "Facility ID", "Date", "Time", "Type", "Status", "Reason"},
                0
        );

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTextField searchField = new JTextField(22);
        TableSearchUtil.attach(table, searchField);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Search:"));
        top.add(searchField);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showAppointments(List<Appointment> appointments) {
        model.setRowCount(0);

        for (Appointment a : appointments) {
            model.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getPatientId(),
                    a.getClinicianId(),
                    a.getFacilityId(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getAppointmentType(),
                    a.getStatus(),
                    a.getReasonForVisit()
            });
        }
    }
}
