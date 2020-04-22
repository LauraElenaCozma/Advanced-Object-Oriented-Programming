package Model;

public class Child extends Client{

    private int age;

    public Child(int age) {
        this.age = age;
    }

    public Child(String name, String email, String phoneNumber, int age) {
        super(name, email, phoneNumber);
        this.age = age;
    }

    public Child(int idClient, String name, String email, String phoneNumber, int age) {
        super(idClient, name, email, phoneNumber);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public double computeDiscount() {
        return 0.3;
    }

    @Override
    public String toString() {
        return "Child{" +
                super.toString() + ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Child)) {
            return false;
        }
        Child c = (Child) o;

        // Compare the data members and return accordingly
        return super.equals((Client)c) && age == c.getAge();
    }
}
