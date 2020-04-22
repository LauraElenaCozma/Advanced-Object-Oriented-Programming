package Model;

public class Pensioner extends Client{
    public Pensioner() {
    }

    public Pensioner(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    public Pensioner(int idClient, String name, String email, String phoneNumber) {
        super(idClient, name, email, phoneNumber);
    }

    @Override
    public double computeDiscount() {

        return 0.4;
    }

    @Override
    public String toString() {

        return "Pensioner{ " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pensioner)) {
            return false;
        }
        Client c = (Client) o;

        // Compare the data members and return accordingly
        return super.equals(c);
    }
}
