package view;

import controller.*;
import data.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JLabel statusLabel;

    public MainFrame() {
        setTitle("UK Healthcare Management System");
        setSize(1400, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        JLabel header = new JLabel(" UK Healthcare Management System");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
        header.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JTabbedPane tabs = new JTabbedPane();

        PatientController patientController =
                new PatientController(new PatientFileRepository("datafiles/patients.csv"));
        PatientsPanel patientsPanel = new PatientsPanel(patientController, this::setStatus);

        CliniciansPanel cliniciansPanel = new CliniciansPanel();
        cliniciansPanel.showClinicians(
                new ClinicianController(new ClinicianFileRepository("datafiles/clinicians.csv"))
                        .getAllClinicians()
        );

        AppointmentsPanel appointmentsPanel = new AppointmentsPanel();
        appointmentsPanel.showAppointments(
                new AppointmentController(new AppointmentFileRepository("datafiles/appointments.csv"))
                        .getAllAppointments()
        );

        PrescriptionController prescriptionController =
                new PrescriptionController(
                        new PrescriptionFileRepository("datafiles/prescriptions.csv"),
                        "datafiles/prescriptions.csv"
                );
        PrescriptionsPanel prescriptionsPanel =
                new PrescriptionsPanel(prescriptionController, this::setStatus);

        ReferralController referralController =
                new ReferralController(
                        new ReferralFileRepository("datafiles/referrals.csv"),
                        "datafiles/referrals.csv"
                );
        ReferralsPanel referralsPanel =
                new ReferralsPanel(referralController, this::setStatus);

        tabs.addTab("Patients", patientsPanel);
        tabs.addTab("Clinicians", cliniciansPanel);
        tabs.addTab("Appointments", appointmentsPanel);
        tabs.addTab("Prescriptions", prescriptionsPanel);
        tabs.addTab("Referrals", referralsPanel);

        JPanel top = new JPanel(new BorderLayout());
        top.add(header, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(statusLabel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setStatus(String text) {
        statusLabel.setText(text == null ? "" : text);
    }
}
