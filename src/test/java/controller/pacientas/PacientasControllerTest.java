package controller.pacientas;

import entity.Pacientas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.PacientasService;
import utilities.input.InputReceiver;
import utilities.output.OutputProducer;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/***
 * Example of tests for the controller using mockito
 * We can test that the controller acts as expected on certain inputs.
 * @author Ignas Ivoska
 *
 */
@ExtendWith(MockitoExtension.class)
class PacientasControllerTest {

    @Mock
    private PacientasService pacientasService;
    @Mock
    private InputReceiver receiver;
    @Mock
    private OutputProducer output;

    @Mock
    private Pacientas pacientasOne;
    @Mock
    private Pacientas pacientasTwo;

    @InjectMocks
    private PacientasController pacientasController;

    @Test
    public void exits() {
        given(receiver.receiveLine()).willReturn("B");

        pacientasController.run();

        then(output).should().produce("===== AUTHOR MANAGEMENT =====");
        then(output).should().produce("L - List of authors");
        then(output).should().produce("F - Find author");
        then(output).should().produce("C - Create new author");
        then(output).should().produce("B - Return");
        then(receiver).should().receiveLine();
        then(pacientasService).shouldHaveNoInteractions();
    }

    @Test
    public void triesToPrintAllAuthorsAndExits() {
        given(receiver.receiveLine()).willReturn("L", "B");
        given(pacientasService.findAllAuthors()).willReturn(asList(pacientasOne, pacientasTwo));
        given(pacientasOne.toString()).willReturn("1: Pirmas Autorius");
        given(pacientasTwo.toString()).willReturn("2: Antras Autorius");

        pacientasController.run();

        then(output).should().produce("===== AUTHOR MANAGEMENT =====");
        then(output).should().produce("L - List of authors");
        then(output).should().produce("F - Find author");
        then(output).should().produce("C - Create new author");
        then(output).should().produce("B - Return");
        then(receiver).should(times(2)).receiveLine();
        then(output).should().produce("==== All authors ====");
        then(pacientasService).should().findAllAuthors();
        then(output).should().produce("1: Pirmas Autorius");
        then(output).should().produce("2: Antras Autorius");
        then(output).should().produce("=====================");
    }

    @Test
    public void triesToFindAuthorByFragmentAndExits() {
        given(receiver.receiveLine()).willReturn("F", "Autor", "B");
        given(pacientasService.findAuthorsByNameFragment("Autor")).willReturn(asList(pacientasOne, pacientasTwo));
        given(pacientasOne.toString()).willReturn("1: Pirmas Autorius");
        given(pacientasTwo.toString()).willReturn("2: Antras Autorius");

        pacientasController.run();

        then(output).should().produce("===== AUTHOR MANAGEMENT =====");
        then(output).should().produce("L - List of authors");
        then(output).should().produce("F - Find author");
        then(output).should().produce("C - Create new author");
        then(output).should().produce("B - Return");
        then(receiver).should(times(3)).receiveLine();
        then(output).should().produce("Enter to fragment to search by");
        then(pacientasService).should().findAuthorsByNameFragment("Autor");
        then(output).should().produce("Found authors:");
        then(output).should().produce("1: Pirmas Autorius");
        then(output).should().produce("2: Antras Autorius");
    }

    @Test
    public void triesToSaveAuthorAndExits() {
        given(receiver.receiveLine()).willReturn("C", "Pirmas", "Autorius", "B");

        pacientasController.run();

        then(output).should().produce("===== AUTHOR MANAGEMENT =====");
        then(output).should().produce("L - List of authors");
        then(output).should().produce("F - Find author");
        then(output).should().produce("C - Create new author");
        then(output).should().produce("B - Return");
        then(receiver).should(times(4)).receiveLine();
        then(output).should().produce("First name of the new author:");
        then(output).should().produce("Last name of the new author:");
        then(pacientasService).should().saveNewPacientas("Pirmas", "Autorius");
        then(output).should().produce("New author saved successfully!");
    }

    @Test
    public void asksForAnotherInputWhenFirstOneIsUnclear() {
        given(receiver.receiveLine()).willReturn(".",  "B");

        pacientasController.run();

        then(output).should().produce("===== AUTHOR MANAGEMENT =====");
        then(output).should(times(2)).produce("L - List of authors");
        then(output).should(times(2)).produce("F - Find author");
        then(output).should(times(2)).produce("C - Create new author");
        then(output).should(times(2)).produce("B - Return");
        then(receiver).should(times(2)).receiveLine();
        then(output).should().produce("Action unrecognised.");
    }
}