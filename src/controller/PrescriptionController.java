package controller;

import data.PrescriptionFileRepository;
import model.Prescription;

import java.util.List;

public class PrescriptionController {

    private PrescriptionFileRepository repo;

    public PrescriptionController(PrescriptionFileRepository repo) {
        this.repo = repo;
    }

    public List<Prescription> getAllPrescriptions() {
        return repo.loadAll();
    }
}
