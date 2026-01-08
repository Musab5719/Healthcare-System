package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class PatientsPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private PatientController controller;
    private Consumer<String> status;

    public PatientsPanel(PatientController controller, Consumer<String> status) {
        this.controller = controller;
        this.status = status;

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "First", "Last", "DOB", "NHS", "Phone", "Email", "GP Surgery"},
                0
        );

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTextField searchField = new JTextField(22);
        TableSearchUtil.attach(table, searchField);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(addBtn);
        top.add(editBtn);
        top.add(deleteBtn);

        addBtn.addActionListener(e -> {
            PatientFormDialog d = new PatientFormDialog(null);
            d.setVisible(true);
            Patient p = d.getResult();
            if (p != null) {
                controller.addPatient(p);
                refresh();
                status.accept("Added patient " + p.getPatientId());
            }
        });

        editBtn.addActionListener(e -> {
            Patient selected = getSelectedPatient();
            if (selected == null) return;

            PatientFormDialog d = new PatientFormDialog(selected);
            d.setVisible(true);
            Patient updated = d.getResult();
            if (updated != null) {
                controller.updatePatient(updated);
                refresh();
                status.accept("Updated patient " + updated.getPatientId());
            }
        });

        deleteBtn.addActionListener(e -> {
            Patient selected = getSelectedPatient();
            if (selected == null) return;

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Delete patient " + selected.getPatientId() + "?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                controller.deletePatientById(selected.getPatientId());
                refresh();
                status.accept("Deleted patient " + selected.getPatientId());
            }
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
        status.accept("Loaded patients: " + model.getRowCount());
    }

    public void refresh() {
        showPatients(controller.getAllPatients());
    }

    private void showPatients(List<Patient> patients) {
        model.setRowCount(0);

        for (Patient p : patients) {
            model.addRow(new Object[]{
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getPhoneNumber(),
                    p.getEmail(),
                    p.getGpSurgeryId()
            });
        }
    }

    private Patient getSelectedPatient() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) return null;

        int row = table.convertRowIndexToModel(viewRow);

        String id = String.valueOf(model.getValueAt(row, 0));
        String first = String.valueOf(model.getValueAt(row, 1));
        String last = String.valueOf(model.getValueAt(row, 2));
        String dob = String.valueOf(model.getValueAt(row, 3));
        String nhs = String.valueOf(model.getValueAt(row, 4));
        String phone = String.valueOf(model.getValueAt(row, 5));
        String email = String.valueOf(model.getValueAt(row, 6));
        String gp = String.valueOf(model.getValueAt(row, 7));

        return new Patient(id, first, last, dob, nhs, "", phone, email, "", "", "", "", "", gp);
    }
}
