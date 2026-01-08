package controller;

import data.PrescriptionFileRepository;
import model.Prescription;
import model.PrescriptionManager;

import java.util.List;

public class PrescriptionController {

    private PrescriptionFileRepository repo;
    private String prescriptionsCsvPath;
    private PrescriptionManager manager;

    public PrescriptionController(PrescriptionFileRepository repo, String prescriptionsCsvPath) {
        this.repo = repo;
        this.prescriptionsCsvPath = prescriptionsCsvPath;
        this.manager = new PrescriptionManager();
    }

    public List<Prescription> getAllPrescriptions() {
        return repo.loadAll();
    }

    public Prescription createPrescription(String patientId,
                                           String clinicianId,
                                           String appointmentId,
                                           String medicationName,
                                           String dosage,
                                           String frequency,
                                           String durationDays,
                                           String conditionTreated,
                                           String instructions,
                                           String pharmacyName,
                                           String status) {

        return manager.createPrescription(
                prescriptionsCsvPath,
                patientId,
                clinicianId,
                appointmentId,
                medicationName,
                dosage,
                frequency,
                durationDays,
                conditionTreated,
                instructions,
                pharmacyName,
                status
        );
    }
}
