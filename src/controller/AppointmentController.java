package controller;

import data.AppointmentFileRepository;
import model.Appointment;

import java.util.List;

public class AppointmentController {

    private AppointmentFileRepository repo;

    public AppointmentController(AppointmentFileRepository repo) {
        this.repo = repo;
    }

    public List<Appointment> getAllAppointments() {
        return repo.loadAll();
    }
}
