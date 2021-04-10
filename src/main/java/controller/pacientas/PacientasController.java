package controller.pacientas;

import controller.Controller;
import entity.Diena;
import entity.Pacientas;
import entity.Miestai;
import service.DienaService;
import service.PacientasService;
import service.MiestaiService;
import utilities.input.InputReceiver;
import utilities.output.OutputProducer;

import java.util.List;

public class PacientasController implements Controller {

    private final PacientasService pacientasService;
    private final InputReceiver receiver;
    private final OutputProducer output;
    private final MiestaiService miestaiService;
    private final DienaService dienaService;

    public PacientasController(PacientasService pacientasService,
                               InputReceiver receiver,
                               OutputProducer output,
                               MiestaiService miestaiService, DienaService dienaService) {
        this.pacientasService = pacientasService;
        this.receiver = receiver;
        this.output = output;
        this.miestaiService = miestaiService;
        this.dienaService = dienaService;
    }

    @Override
    public void run() {
        output.produce("===== Amžiaus grupės =====");
        printInstructions();
        receiveInputAndAct();
    }

    private void printInstructions() {
        output.produce("3 amžiaus grupė: 18-64 -  dar neskiepijam");
        output.produce("2 amžiaus grupė: 65-79 - uz savaites");
        output.produce("1 amžiaus grupė: 80+ - rytoj");
        output.produce("---------------------------------------");
        output.produce("Įveskite savo amžių");

    }

    private void receiveInputAndAct() {
        int input = Integer.parseInt(receiver.receiveLine().toUpperCase());
        int inputForTheCase = 0;

        if (input > 17 && input < 65) {
            inputForTheCase = 1;
        } else if (input > 64 && input < 80) {
            inputForTheCase = 2;
        } else if (input > 79) {
            inputForTheCase = 3;
        }

        switch (inputForTheCase) {
            case 1: {
                listPirmaGrupe();
                break;
            }
            case 2: {
                listAntraGrupe();
                break;
            }
            case 3: {
                listTreciaGrupe();
                break;
            }
            default: {
                output.produce("Įveskite teisingą amžių");
                printInstructions();
            }
        }
        receiveInputAndAct();
    }

    private void listTreciaGrupe() {
        output.produce("Jūs esate 3 amžiaus grupėje");
        output.produce("Įrašykite vardą:");
        String firstName = receiver.receiveLine();
        output.produce("Įrašykite pavardę:");
        String lastName = receiver.receiveLine();
        pacientasService.saveNewPacientas(firstName, lastName);
        output.produce("Įrašykite miestą, kuriame norite skiepytis:");
        List<Miestai> miestais = miestaiService.findMiestaiByFragment(receiver.receiveLine());
        output.produce("Jus paskiepysime šitoje įstaigoje.");
        miestais.forEach(miestai -> output.produce(miestai.toString()));
        output.produce("Jus galime paskiepyti jau rytoj");
        output.produce("Įrašykite mėnesį ir dieną, kurią norite skiepytis (pvz: 4.10):");
        List<Diena> dienas = dienaService.findDienaByFragment(receiver.receiveLine());
        dienas.forEach(diena -> output.produce(diena.toString()));
        output.produce("---------------------------------------");
        output.produce("Pilna informacija");
        System.out.print("Primename, kad ");
        dienas.forEach(diena -> output.produce(diena.toString()));
        System.out.print("laukiam Jūsų ");
        miestais.forEach(miestai -> output.produce(miestai.toString()));
        System.out.println("Atvykstant prašome dėvėti kaukę.");
        System.out.println("Pas mus gyva eilė, mūsų darbo laikas nuo 10,00 iki 17,00.");
        System.out.println("Vykdami vakcinuotis, turėkite asmens dokumentą.");
        System.out.println("Jei atvykstate pakartotiniam skiepui," +
                " turėkite ir pirmo vizito metu jums išduotą skiepijimo kortelę.");

    }

    private void listAntraGrupe() {
        output.produce("Jūs esate 2 amžiaus grupėje.");
        output.produce("Jūsų amžiaus grupę pradėsime skiepyti kitą savaitę.");
        output.produce("Įrašykite vardą:");
        String firstName = receiver.receiveLine();
        output.produce("Įrašykite pavardę:");
        String lastName = receiver.receiveLine();
        pacientasService.saveNewPacientas(firstName, lastName);
        output.produce("Įrašykite miestą, kuriame norite skiepytis:");
        List<Miestai> miestais = miestaiService.findMiestaiByFragment(receiver.receiveLine());
        output.produce("Jus paskiepysime šitoje įstaigoje.");
        miestais.forEach(miestai -> output.produce(miestai.toString()));
        output.produce("Jūsų amžiaus grupę pradėsime skiepyti kitą savaitę.");
        output.produce("Įrašykite mėnesį ir dieną, kurią norite skiepytis (pvz: 4.10):");
        List<Diena> dienas = dienaService.findDienaByFragment(receiver.receiveLine());
        dienas.forEach(diena -> output.produce(diena.toString()));
        output.produce("---------------------------------------");
        output.produce("Pilna informacija");
        System.out.print("Primename, kad ");
        dienas.forEach(diena -> output.produce(diena.toString()));
        System.out.print("laukiam Jūsų ");
        miestais.forEach(miestai -> output.produce(miestai.toString()));
        System.out.println("Atvykstant prašome dėvėti kaukę.");
        System.out.println("Pas mus gyva eilė, mūsų darbo laikas nuo 10,00 iki 17,00.");
        System.out.println("Vykdami vakcinuotis, turėkite asmens dokumentą.");
        System.out.println("Jei atvykstate pakartotiniam skiepui," +
                " turėkite ir pirmo vizito metu jums išduotą skiepijimo kortelę.");

    }

    private void listPirmaGrupe() {
        output.produce("Jūs esate 1 amžiaus grupėje");
        output.produce("Šitos amžiaus grupės dar neskiepyjam");
        output.produce("Pranešime, kada skiepysime jūsų amžiaus grupę.");

    }
}


