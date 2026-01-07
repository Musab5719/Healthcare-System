package view;

import controller.ReferralController;
import model.Referral;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReferralsPanel extends JPanel {

    private DefaultTableModel model;
    private ReferralController controller;

    public ReferralsPanel(ReferralController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Patient", "From Clinician", "To Clinician", "From Facility", "To Facility", "Date", "Urgency", "Status"},
                0
        );

        JTable table = new JTable(model);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton createBtn = new JButton("Create Referral");
        top.add(createBtn);

        createBtn.addActionListener(e -> {
            CreateReferralDialog dialog = new CreateReferralDialog(controller);
            dialog.setVisible(true);
            refresh();
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
