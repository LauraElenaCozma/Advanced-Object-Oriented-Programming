package Model;

public class Student extends Client {
    private String noLegit;

    public Student(String noLegit) {
        this.noLegit = noLegit;
    }

    public Student(String name, String email, String phoneNumber, String noLegit) {
        super(name, email, phoneNumber);
        this.noLegit = noLegit;
    }

    public Student(int idClient, String name, String email, String phoneNumber, String noLegit) {
        super(idClient, name, email, phoneNumber);
        this.noLegit = noLegit;
    }

    public String getNoLegit() {

        return noLegit;
    }

    public void setNoLegit(String noLegit) {

        this.noLegit = noLegit;
    }

    @Override
    public double computeDiscount() {
        //the discount for a student is set to 0.5
        return 0.5;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() + "noLegit=" + noLegit + ' ' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student c = (Student) o;

        // Compare the data members and return accordingly
        return super.equals((Client)c) && noLegit.equals(c.getNoLegit());
    }
}
