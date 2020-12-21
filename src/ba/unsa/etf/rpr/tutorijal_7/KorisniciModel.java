package ba.unsa.etf.rpr.tutorijal_7;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();


    public void napuni() {
        korisnici.add(new Korisnik("Emil", "Fejzagic", "efejzagic2@etf.unsa.ba", "efejzagic2", "lozinka"));
        korisnici.add(new Korisnik("Mujo", "Mujic", "mmujic2@etf.unsa.ba", "mmujic2", "lozinka"));
        trenutniKorisnik.set(null);
    }


    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }


    public void addKorisnik (Korisnik korisnik) {
        korisnici.add(korisnik);
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }


}
