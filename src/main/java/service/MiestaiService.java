package service;

import entity.Miestai;
import repository.miestai.MiestaiRepository;

import java.util.List;

public class MiestaiService {

    private final MiestaiRepository miestaiRepository;


    public MiestaiService(MiestaiRepository miestaiRepository) {
        this.miestaiRepository = miestaiRepository;
    }

    public List<Miestai> findAllMiestai() {
        return miestaiRepository.findAll();
    }

    public List<Miestai> findMiestaiByFragment(String fragment) {
        return miestaiRepository.searchByFragment(fragment);
    }
}
