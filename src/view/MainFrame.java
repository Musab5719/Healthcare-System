package view;

import controller.AppointmentController;
import controller.ClinicianController;
import controller.FacilityController;
import controller.PatientController;
import data.AppointmentFileRepository;
import data.ClinicianFileRepository;
import data.FacilityFileRepository;
import data.PatientFileRepository;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("UK Healthcare Management System");
        setSize(1250, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        PatientsPanel patientsPanel = new PatientsPanel();
        CliniciansPanel cliniciansPanel = new CliniciansPanel();
        FacilitiesPanel facilitiesPanel = new FacilitiesPanel();
        AppointmentsPanel appointmentsPanel = new AppointmentsPanel();

        tabs.addTab("Patients", patientsPanel);
        tabs.addTab("Clinicians", cliniciansPanel);
        tabs.addTab("Facilities", facilitiesPanel);
        tabs.addTab("Appointments", appointmentsPanel);

        add(tabs, BorderLayout.CENTER);

        PatientController patientController = new PatientController(new PatientFileRepository("datafiles/patients.csv"));
        patientsPanel.showPatients(patientController.getAllPatients());

        ClinicianController clinicianController = new ClinicianController(new ClinicianFileRepository("datafiles/clinicians.csv"));
        cliniciansPanel.showClinicians(clinicianController.getAllClinicians());

        FacilityController facilityController = new FacilityController(new FacilityFileRepository("datafiles/facilities.csv"));
        facilitiesPanel.showFacilities(facilityController.getAllFacilities());

        AppointmentController appointmentController = new AppointmentController(new AppointmentFileRepository("datafiles/appointments.csv"));
        appointmentsPanel.showAppointments(appointmentController.getAllAppointments());

        setVisible(true);
    }
}
