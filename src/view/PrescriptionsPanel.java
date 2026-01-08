package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class PrescriptionsPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private PrescriptionController controller;
    private Consumer<String> status;

    public PrescriptionsPanel(PrescriptionController controller, Consumer<String> status) {
        this.controller = controller;
        this.status = status;

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient", "Clinician", "Medication", "Dosage", "Frequency", "Status", "Pharmacy"},
                0
        );

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton createBtn = new JButton("Create Prescription");
        top.add(createBtn);

        createBtn.addActionListener(e -> {
            CreatePrescriptionDialog dialog = new CreatePrescriptionDialog(controller);
            dialog.setVisible(true);
            refresh();
            status.accept("Prescription created (saved to CSV + outputs)");
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        showPrescriptions(controller.getAllPrescriptions());
    }

    private void showPrescriptions(List<Prescription> prescriptions) {
        model.setRowCount(0);

        for (Prescription p : prescriptions) {
            model.addRow(new Object[]{
                    p.getPrescriptionId(),
                    p.getPatientId(),
                    p.getClinicianId(),
                    p.getMedicationName(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getStatus(),
                    p.getPharmacyName()
            });
        }
    }
}
