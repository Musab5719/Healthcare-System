package view;

import controller.*;
import data.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("UK Healthcare Management System");
        setSize(1350, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        PatientsPanel patientsPanel = new PatientsPanel();
        CliniciansPanel cliniciansPanel = new CliniciansPanel();
        FacilitiesPanel facilitiesPanel = new FacilitiesPanel();
        AppointmentsPanel appointmentsPanel = new AppointmentsPanel();
        PrescriptionsPanel prescriptionsPanel = new PrescriptionsPanel();

        ReferralController referralController =
                new ReferralController(new ReferralFileRepository("datafiles/referrals.csv"), "datafiles/referrals.csv");
        ReferralsPanel referralsPanel = new ReferralsPanel(referralController);

        tabs.addTab("Patients", patientsPanel);
        tabs.addTab("Clinicians", cliniciansPanel);
        tabs.addTab("Facilities", facilitiesPanel);
        tabs.addTab("Appointments", appointmentsPanel);
        tabs.addTab("Prescriptions", prescriptionsPanel);
        tabs.addTab("Referrals", referralsPanel);

        add(tabs, BorderLayout.CENTER);

        patientsPanel.showPatients(new PatientController(new PatientFileRepository("datafiles/patients.csv")).getAllPatients());
        cliniciansPanel.showClinicians(new ClinicianController(new ClinicianFileRepository("datafiles/clinicians.csv")).getAllClinicians());
        facilitiesPanel.showFacilities(new FacilityController(new FacilityFileRepository("datafiles/facilities.csv")).getAllFacilities());
        appointmentsPanel.showAppointments(new AppointmentController(new AppointmentFileRepository("datafiles/appointments.csv")).getAllAppointments());
        prescriptionsPanel.showPrescriptions(new PrescriptionController(new PrescriptionFileRepository("datafiles/prescriptions.csv")).getAllPrescriptions());

        setVisible(true);
    }
}
