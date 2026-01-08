package controller;

import data.PatientFileRepository;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private PatientFileRepository repo;

    public PatientController(PatientFileRepository repo) {
        this.repo = repo;
    }

    public List<Patient> getAllPatients() {
        return repo.loadAll();
    }

    public void addPatient(Patient patient) {
        List<Patient> patients = new ArrayList<>(repo.loadAll());
        patients.add(patient);
        repo.saveAll(patients);
    }

    public void updatePatient(Patient updated) {
        List<Patient> patients = new ArrayList<>(repo.loadAll());

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(updated.getPatientId())) {
                patients.set(i, updated);
                break;
            }
        }

        repo.saveAll(patients);
    }

    public void deletePatientById(String patientId) {
        List<Patient> patients = new ArrayList<>(repo.loadAll());
        patients.removeIf(p -> p.getPatientId().equals(patientId));
        repo.saveAll(patients);
    }
}
