package School;

public class Student {
    private String name;
    private double medSem1;
    private double medSem2;

    public Student(String n , double med1 , double med2)
    {
            name = n;
            medSem1 = med1;
            medSem2 = med2;
    }
    public Student()
    {
        this("" , 0 , 0);
    }
    public String toString()
    {
        String res = new String();
        res += "Nume: " + name + " Media pe semestrul 1: " + medSem1 + " Media pe semestrul 2: " + medSem2;
        return res;
    }
    public double getMedAn1()
    {
        return (medSem1 + medSem2) / 2.0;
    }
}
