package controller;

import data.ReferralFileRepository;
import model.Referral;
import model.ReferralManager;

import java.util.List;

public class ReferralController {

    private ReferralFileRepository repo;
    private String referralsCsvPath;

    public ReferralController(ReferralFileRepository repo, String referralsCsvPath) {
        this.repo = repo;
        this.referralsCsvPath = referralsCsvPath;
    }

    public List<Referral> getAllReferrals() {
        return repo.loadAll();
    }

    public Referral createReferral(String patientId,
                                   String referringClinicianId,
                                   String referredToClinicianId,
                                   String referringFacilityId,
                                   String referredToFacilityId,
                                   String urgencyLevel,
                                   String reasonForReferral,
                                   String clinicalSummary,
                                   String instructions,
                                   String appointmentId) {

        return ReferralManager.getInstance().createReferral(
                referralsCsvPath,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                urgencyLevel,
                reasonForReferral,
                clinicalSummary,
                instructions,
                appointmentId
        );
    }
}
