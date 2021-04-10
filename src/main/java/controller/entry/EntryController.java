package controller.entry;

import controller.Controller;
import utilities.input.InputReceiver;
import utilities.output.OutputProducer;

public class EntryController implements Controller {

    private final Controller pacientasController;
    private final InputReceiver receiver;
    private final OutputProducer output;

    public EntryController(Controller pacientasController,
                           InputReceiver receiver,
                           OutputProducer output) {
        this.pacientasController = pacientasController;
        this.receiver = receiver;
        this.output = output;
    }

    @Override
    public void run() {
        output.produce("===== Skiepų sistema AZA =====");
        printInstructions();
    }

    private void printInstructions() {
        output.produce("Užpildykite privalomus laukus");
        pacientasController.run();

    }
}
