package Model;

public class Adult extends Client {

    public Adult() {
    }

    public Adult(String name, String email, String phoneNumber) {

        super(name, email, phoneNumber);
    }

    public Adult(int idClient, String name, String email, String phoneNumber) {

        super(idClient, name, email, phoneNumber);
    }

    @Override
    public double computeDiscount() {
        //there is no discount
        return 0;
    }

    @Override
    public String toString() {
        return "Adult{ " +
                super.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Adult)) {
            return false;
        }
        Client c = (Client) o;

        // Compare the data members and return accordingly
        return super.equals(c);
    }
}
