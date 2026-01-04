package controller;

import data.PatientFileRepository;
import model.Patient;

import java.util.List;

public class PatientController {

    private PatientFileRepository repo;

    public PatientController(PatientFileRepository repo) {
        this.repo = repo;
    }

    public List<Patient> getAllPatients() {
        return repo.loadAll();
    }
}
