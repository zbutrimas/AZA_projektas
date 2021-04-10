package service;

import entity.Diena;
import entity.Miestai;
import repository.diena.DienaRepository;

import java.util.List;

public class DienaService {

    private final DienaRepository dienaRepository;

    public DienaService(DienaRepository dienaRepository) {
        this.dienaRepository = dienaRepository;
    }
    public List<Diena> findAllDiena() {
        return dienaRepository.findAll();
    }
    public List<Diena> findDienaByFragment(String fragment) {
        return dienaRepository.searchByDienaFragment(fragment);
    }
}
