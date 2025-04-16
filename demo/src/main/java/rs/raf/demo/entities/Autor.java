package rs.raf.demo.entities;

public class Autor {

    private  String firstName;
    private  String lastName;

    public  Autor(){}
    public  Autor(String fristName,String lastName){
        this.firstName=fristName;
        this.lastName=lastName;
    }
    public String getFullName(){
        return ""+this.getFirstName()+" "+this.getLastName();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
