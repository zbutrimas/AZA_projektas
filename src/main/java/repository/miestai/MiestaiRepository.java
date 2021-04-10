package repository.miestai;

import entity.Miestai;
import repository.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MiestaiRepository extends AbstractRepository<Miestai, Long> {

    public MiestaiRepository(EntityManager entityManager) {
        super(entityManager, Miestai.class);
    }

    @Override
    public List<Miestai> findAll() {
        return entityManager.createQuery("FROM Miestai ", Miestai.class).getResultList();
    }

    public List<Miestai> searchByFragment(String fragment) {
        Query query = entityManager.createQuery("FROM Miestai WHERE miestoPavadinimas LIKE :nameFragment", Miestai.class);
        query.setParameter("nameFragment", "%" + fragment + "%");
        return query.getResultList();
    }
}
