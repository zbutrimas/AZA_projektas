package repository.diena;

import entity.Diena;
import entity.Miestai;
import entity.Pacientas;
import repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DienaRepository extends AbstractRepository<Diena, Long> {

    public DienaRepository(EntityManager entityManager) {
        super(entityManager, Diena.class);
    }

    @Override
    public List<Diena> findAll() {
        return null;
    }
    public List<Diena> searchByDienaFragment(String fragment) {
        Query query = entityManager.createQuery("FROM Diena WHERE menesioDiena LIKE :nameFragment", Diena.class);
        query.setParameter("nameFragment", "%" + fragment + "%");
        return query.getResultList();
    }
}
