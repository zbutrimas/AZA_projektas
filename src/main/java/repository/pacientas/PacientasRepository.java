package repository.pacientas;

import entity.Pacientas;
import repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PacientasRepository extends AbstractRepository<Pacientas, Long> {

    public PacientasRepository(EntityManager entityManager) {
        super(entityManager, Pacientas.class);
    }

    @Override
    public List<Pacientas> findAll() {
        return entityManager.createQuery("FROM Pacientas", Pacientas.class).getResultList();
    }


    public List<Pacientas> searchByNameFragment(String fragment) {
        Query query = entityManager.createQuery("FROM Pacientas WHERE firstName LIKE :nameFragment" +
                " OR lastName LIKE :nameFragment", Pacientas.class);
        query.setParameter("nameFragment", "%" + fragment + "%");
        return query.getResultList();
    }

}
