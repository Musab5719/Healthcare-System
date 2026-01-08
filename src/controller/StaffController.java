package controller;

import data.StaffFileRepository;
import model.Staff;

import java.util.List;

public class StaffController {

    private StaffFileRepository repo;

    public StaffController(StaffFileRepository repo) {
        this.repo = repo;
    }

    public List<Staff> getAllStaff() {
        return repo.loadAll();
    }
}
