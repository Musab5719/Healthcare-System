package view;

import controller.PatientController;
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
        tabs.addTab("Patients", patientsPanel);

        add(tabs, BorderLayout.CENTER);

        PatientFileRepository repo = new PatientFileRepository("datafiles/patients.csv");
        PatientController controller = new PatientController(repo);
        patientsPanel.showPatients(controller.getAllPatients());

        setVisible(true);
    }
}
