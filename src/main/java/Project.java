import controller.pacientas.PacientasController;
import controller.entry.EntryController;
import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.diena.DienaRepository;
import repository.pacientas.PacientasRepository;
import repository.miestai.MiestaiRepository;
import service.DienaService;
import service.PacientasService;
import service.MiestaiService;
import utilities.input.DefaultInputReceiver;
import utilities.input.InputReceiver;
import utilities.output.DefaultOutputProducer;
import utilities.output.OutputProducer;

import javax.persistence.EntityManager;

public class Project {

    public static final String HIBERNATE_CONFIGURATION = "hibernate.cfg.xml";

    public Project() {
        constructEntryController(entityManager()).run();
    }

    private EntryController constructEntryController(EntityManager entityManager) {
        InputReceiver receiver = new DefaultInputReceiver();
        OutputProducer output = new DefaultOutputProducer();
        PacientasService pacientasService = new PacientasService(new PacientasRepository(entityManager));
        MiestaiService miestaiService = new MiestaiService(new MiestaiRepository(entityManager));
        DienaService dienaService = new DienaService(new DienaRepository(entityManager));

        PacientasController pacientasController = new PacientasController
                (pacientasService, receiver, output, miestaiService, dienaService);
        return new EntryController(pacientasController, receiver, output);
    }

    private EntityManager entityManager() {
        SessionFactory sessionFactory = new Configuration()
                .configure(HIBERNATE_CONFIGURATION)
                .addAnnotatedClass(Pacientas.class)
                .addAnnotatedClass(Miestai.class)
                .addAnnotatedClass(Diena.class)
                .buildSessionFactory();

        return sessionFactory.createEntityManager();
    }

}
