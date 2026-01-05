package controller;

import data.ClinicianFileRepository;
import model.Clinician;

import java.util.List;

public class ClinicianController {

    private ClinicianFileRepository repo;

    public ClinicianController(ClinicianFileRepository repo) {
        this.repo = repo;
    }

    public List<Clinician> getAllClinicians() {
        return repo.loadAll();
    }
}
