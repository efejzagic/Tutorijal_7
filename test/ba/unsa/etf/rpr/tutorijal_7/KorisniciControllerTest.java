package ba.unsa.etf.rpr.tutorijal_7;

import static org.junit.jupiter.api.Assertions.*;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.ListViewAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;



@ExtendWith(ApplicationExtension.class)

class KorisniciControllerTest {
    private static Stage stage1;
    private KorisniciModel model;
    @Start
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/korisnici.fxml"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
        stage1=stage;
    }

    @AfterEach
    public void izbirsiPodatke(FxRobot robot) {
        //Platform.runLater(() -> stage1.close());

        robot.clickOn("#fldIme");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);

        robot.clickOn("#fldPrezime");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);

        robot.clickOn("#fldMail");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);

        robot.clickOn("#fldUsername");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);

        robot.clickOn("#fldLozinka");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);
    }


    @BeforeEach
    public void unesiPodatke(FxRobot robot) {

        robot.clickOn("#fldIme").write("Mirza");
        robot.clickOn("#fldPrezime").write("Mirzic");
        robot.clickOn("#fldMail").write("mmirzic1@etf.unsa.ba");
        robot.clickOn("#fldUsername").write("mmirzic1");
        robot.clickOn("#fldLozinka").write("lozinka");
    }

    @Test
    public void dodajKorisnikaSaPodacima(FxRobot robot) {
        Button btnDodaj = robot.lookup("#btnDodaj").queryAs(Button.class);
        assertNotNull(btnDodaj);
        robot.clickOn(btnDodaj);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dp = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Uspjesno ste unijeli podatke", dp.getHeaderText());

        Button okButton = (Button) dp.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        assertTrue(stage1.isShowing());
    }

    @Test
    public void pogresniPodaci(FxRobot robot) {
        robot.clickOn("#fldIme");
        robot.press(KeyCode.CONTROL).press(KeyCode.A).release(KeyCode.CONTROL).release(KeyCode.A).press(KeyCode.BACK_SPACE).release(KeyCode.BACK_SPACE);

        Button btnDodaj = robot.lookup("#btnDodaj").queryAs(Button.class);
        assertNotNull(btnDodaj);
        robot.clickOn(btnDodaj);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dp = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Pogresno ste unijeli podatke", dp.getHeaderText());

        Button okButton = (Button) dp.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        assertTrue(stage1.isShowing());
    }

    @Test
    public void korisnikIzListe(FxRobot robot) {
        ListView listKorisnici = robot.lookup("#listKorisnici").queryAs(ListView.class);
        int provjera=0;
        for(int i=0; i<listKorisnici.getItems().size(); i++) {
            if(listKorisnici.getItems().get(i).getClass().getName().equals("Mirza Mirzic")) provjera=i;
        }
        if(provjera!=0)
            assertEquals("Mirzic Mirza" , listKorisnici.getItems().get(provjera).getClass().getName());
    }

}