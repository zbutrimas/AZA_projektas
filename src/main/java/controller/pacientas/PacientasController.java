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
        switch (input) {
            case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27:
            case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37:
            case 38: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47:
            case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57:
            case 58: case 59: case 60: case 61: case 62: case 63: case 64: {
                listPirmaGrupe();
                break;
            }
            case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74:
            case 75: case 76: case 77: case 78: case 79: {
                listAntraGrupe();
                break;
            }
            case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89:
            case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99:
            case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 109:
            case 110: case 111: case 112: case 113: case 114: case 115: case 116: case 117: case 118: case 119: {
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


