package controller;

import data.FacilityFileRepository;
import model.Facility;

import java.util.List;

public class FacilityController {

    private FacilityFileRepository repo;

    public FacilityController(FacilityFileRepository repo) {
        this.repo = repo;
    }

    public List<Facility> getAllFacilities() {
        return repo.loadAll();
    }
}
