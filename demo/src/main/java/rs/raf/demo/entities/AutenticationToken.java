package rs.raf.demo.entities;

public class AutenticationToken {

    private String ime;
    private String prezime;
    private String email;
    private String tip;
    private String status;

    public AutenticationToken(String ime, String prezime, String email, String tip, String status) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tip = tip;
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
