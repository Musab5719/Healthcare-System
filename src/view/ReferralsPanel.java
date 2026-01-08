package view;

import controller.ReferralController;
import model.Referral;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ReferralsPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private ReferralController controller;
    private Consumer<String> status;

    public ReferralsPanel(ReferralController controller, Consumer<String> status) {
        this.controller = controller;
        this.status = status;

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient", "From Clinician", "To Clinician", "From Facility", "To Facility", "Date", "Urgency", "Status"},
                0
        );

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton createBtn = new JButton("Create Referral");
        top.add(createBtn);

        createBtn.addActionListener(e -> {
            CreateReferralDialog dialog = new CreateReferralDialog(controller);
            dialog.setVisible(true);
            refresh();
            status.accept("Referral created (Singleton) and saved to outputs");
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        showReferrals(controller.getAllReferrals());
    }

    private void showReferrals(List<Referral> referrals) {
        model.setRowCount(0);

        for (Referral r : referrals) {
            model.addRow(new Object[]{
                    r.getReferralId(),
                    r.getPatientId(),
                    r.getReferringClinicianId(),
                    r.getReferredToClinicianId(),
                    r.getReferringFacilityId(),
                    r.getReferredToFacilityId(),
                    r.getReferralDate(),
                    r.getUrgencyLevel(),
                    r.getStatus()
            });
        }
    }
}
