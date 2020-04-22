package Model;

public abstract class Client {
    private static int uniqueId = 1;
    protected int idClient;
    protected String name;
    protected String email;
    protected String phoneNumber;


    public Client() {
        this.idClient = uniqueId;
        uniqueId++;
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
    }

    public Client(String name, String email, String phoneNumber) {
        this.idClient = uniqueId;
        uniqueId++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client(int idClient, String name, String email, String phoneNumber) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        Client.uniqueId = uniqueId;
    }

    public abstract double computeDiscount();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return  "id=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber+
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        Client c = (Client) o;

        // Compare the data members and return accordingly
        return name.equals(c.getName()) && phoneNumber.equals(c.getPhoneNumber()) && idClient == c.getIdClient();
    }


}
