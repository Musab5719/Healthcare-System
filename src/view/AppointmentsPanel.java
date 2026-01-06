package view;

import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AppointmentsPanel extends JPanel {

    private DefaultTableModel model;

    public AppointmentsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient ID", "Clinician ID", "Facility ID", "Date", "Time", "Type", "Status", "Reason"},
                0
        );

        JTable table = new JTable(model);
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
