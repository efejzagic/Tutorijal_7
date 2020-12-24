package ba.unsa.etf.rpr.tutorijal_7;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class KorisniciController {

    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldMail;
    public TextField fldUsername;
    public PasswordField fldLozinka;
    public ListView<Korisnik> listKorisnici;
    public Button btnDodaj;


    private KorisniciModel model = new KorisniciModel();



    public KorisniciController () {
        model.napuni();
    }
    @FXML
    public void initialize() {

        fldIme.getStyleClass().add("neispravno");
        fldPrezime.getStyleClass().add("neispravno");
        fldMail.getStyleClass().add("neispravno");
        fldUsername.getStyleClass().add("neispravno");
        fldLozinka.getStyleClass().add("neispravno");


        listKorisnici.getSelectionModel().selectedItemProperty().addListener(
                (obs,oldKorisnik, newKorisnik) -> {
                    model.setTrenutniKorisnik(newKorisnik);
                    if(oldKorisnik!=null) {
                        //unbind
                        fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                        fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                        fldUsername.textProperty().unbindBidirectional(oldKorisnik.korisnickoImeProperty());
                        fldMail.textProperty().unbindBidirectional(oldKorisnik.mailProperty());
                        fldLozinka.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
                    }
                    if(newKorisnik==null) {
                        fldIme.setText("");
                        fldPrezime.setText("");
                        fldMail.setText("");
                        fldUsername.setText("");
                        fldLozinka.setText("");
                    }
                    else {
                        fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                        fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                        fldUsername.textProperty().bindBidirectional(newKorisnik.korisnickoImeProperty());
                        fldLozinka.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
                        fldMail.textProperty().bindBidirectional(newKorisnik.mailProperty());
                    }

                    listKorisnici.refresh();
                });

        listKorisnici.setItems(model.getKorisnici());




        fldIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fldIme.getText().trim().isEmpty()) {
                    fldIme.getStyleClass().removeAll("ispravno");
                    fldIme.getStyleClass().add("neispravno");
                }
                else {
                    fldIme.getStyleClass().removeAll("neispravno");
                    fldIme.getStyleClass().add("ispravno");
                }
            }
        });

        fldPrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fldPrezime.getText().trim().isEmpty()) {
                    fldPrezime.getStyleClass().removeAll("ispravno");
                    fldPrezime.getStyleClass().add("neispravno");
                }
                else {
                    fldPrezime.getStyleClass().removeAll("neispravno");
                    fldPrezime.getStyleClass().add("ispravno");
                }
            }
        });
        fldMail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                if(fldMail.getText().trim().isEmpty()) {
                    fldMail.getStyleClass().removeAll("ispravno");
                    fldMail.getStyleClass().add("neispravno");
                }
                else {
                    fldMail.getStyleClass().removeAll("neispravno");
                    fldMail.getStyleClass().add("ispravno");
                }
            }
        });
        fldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fldUsername.getText().trim().isEmpty()) {
                    fldUsername.getStyleClass().removeAll("ispravno");
                    fldUsername.getStyleClass().add("neispravno");
                }
                else {
                    fldUsername.getStyleClass().removeAll("neispravno");
                    fldUsername.getStyleClass().add("ispravno");
                }
            }
        });
        fldLozinka.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(fldLozinka.getText().trim().isEmpty() || fldLozinka.getText().length()<6) {
                    fldLozinka.getStyleClass().removeAll("ispravno");
                    fldLozinka.getStyleClass().add("neispravno");
                }
                else {
                    fldLozinka.getStyleClass().removeAll("neispravno");
                    fldLozinka.getStyleClass().add("ispravno");
                }
            }
        });
    }

    public boolean provjera () {
        if(fldIme.getText().isBlank() || fldPrezime.getText().isBlank() || fldMail.getText().isBlank() || fldUsername.getText().isBlank() ||
                fldLozinka.getText().isBlank() || fldLozinka.getText().length()<6) return false;
        return true;
    }

    public void izbrisi() {
        model.setTrenutniKorisnik(null);
    }

    public void actionDodaj(ActionEvent actionEvent) {
        if(provjera()) {
            Korisnik korisnik = new Korisnik(fldIme.getText(), fldPrezime.getText(), fldMail.getText(), fldUsername.getText(), fldLozinka.getText());
            model.addKorisnik(korisnik);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cestitamo");
            alert.setHeaderText("Uspjesno ste unijeli podatke");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska prilikom unosa");
            alert.setHeaderText("Pogresno ste unijeli podatke");
            alert.setContentText("Molimo vas da ispravno unesete Vase podatke!");
            alert.showAndWait();
        }
        izbrisi();
    }

    public void actionKraj(ActionEvent actionEvent) {
        System.exit(0);
    }

}
