package rs.raf.demo.entities;

public class User {
    private int id;
    private String email;
    private String ime;
    private String prezime;
    private String tip;
    private String aktivnost;
    private String hashedPassword;

    public User() {
    }

    public User(int id,String email,String ime,String prezime,String tip,String aktivnost,String hashedPassword){
        this.id=id;
        this.email=email;
        this.ime=ime;
        this.prezime=prezime;
        this.tip=tip;
        this.aktivnost=aktivnost;
        this.hashedPassword=hashedPassword;
    }

    public User(String email, String tip, String hashedPassword) {
        this.email = email;
        this.tip = tip;
        this.hashedPassword = hashedPassword;
    }

    public User(String ime,String prezime,String email,String tip,String hashedPassword) {
        this.ime=ime;
        this.prezime=prezime;
        this.email=email;
        this.tip=tip;
        this.hashedPassword=hashedPassword;
    }
    public User(int id, String email, String ime, String prezime, String tip) {
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
        this.email=email;
        this.tip=tip;
    }
    public User(int id, String email, String ime, String prezime, String tip, String aktivnost) {
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
        this.email=email;
        this.tip=tip;
        this.aktivnost=aktivnost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

    public String getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(String aktivnost) {
        this.aktivnost = aktivnost;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", tip='" + tip + '\'' +
                ", aktivnost='" + aktivnost + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
