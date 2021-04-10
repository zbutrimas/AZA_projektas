package service;

import entity.Pacientas;
import repository.pacientas.PacientasRepository;

import java.util.List;

public class PacientasService {

    private final PacientasRepository pacientasRepository;

    public PacientasService(PacientasRepository pacientasRepository) {
        this.pacientasRepository = pacientasRepository;
    }

    public Pacientas saveNewPacientas(String firstName, String lastName) {
        Pacientas pacientas = new Pacientas(firstName, lastName);
        pacientasRepository.save(pacientas);
        return pacientas;
    }

    public List<Pacientas> findAllAuthors() {
        return pacientasRepository.findAll();
    }

    public Pacientas updateAuthor(Pacientas pacientas) {
        pacientasRepository.save(pacientas);
        return pacientas;
    }

    public List<Pacientas> findAuthorsByNameFragment(String fragment) {
        return pacientasRepository.searchByNameFragment(fragment);
    }
}
