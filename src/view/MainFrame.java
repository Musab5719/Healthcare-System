package view;

import controller.ClinicianController;
import controller.PatientController;
import data.ClinicianFileRepository;
import data.PatientFileRepository;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("UK Healthcare Management System");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        PatientsPanel patientsPanel = new PatientsPanel();
        CliniciansPanel cliniciansPanel = new CliniciansPanel();

        tabs.addTab("Patients", patientsPanel);
        tabs.addTab("Clinicians", cliniciansPanel);

        add(tabs, BorderLayout.CENTER);

        PatientFileRepository patientRepo = new PatientFileRepository("datafiles/patients.csv");
        PatientController patientController = new PatientController(patientRepo);
        patientsPanel.showPatients(patientController.getAllPatients());

        ClinicianFileRepository clinicianRepo = new ClinicianFileRepository("datafiles/clinicians.csv");
        ClinicianController clinicianController = new ClinicianController(clinicianRepo);
        cliniciansPanel.showClinicians(clinicianController.getAllClinicians());

        setVisible(true);
    }
}
