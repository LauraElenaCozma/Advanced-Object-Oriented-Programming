package School;

public class Catalog {
    String grupa;
    Student[] stud;
    public Catalog()
    {
        grupa = "";
        stud = new Student[5];
    }
    public Catalog(String gr , Student s1 , Student s2 , Student s3 , Student s4 , Student s5)
    {
        grupa = gr;
        stud = new Student[]{s1 , s2 , s3 , s4 , s5};
    }
    public String toString()
    {
        String res = new String();
        res += "Grupa " + grupa + " cu studentii:\n";
        for(Student s : stud)
            res += s.toString() + "\n";
        return res;
    }
    public double getMedieClasa()
    {
        int num_stud = stud.length;
        double sum = 0;
        for(Student s : stud)
        {
            sum += s.getMedAn1();
        }
        return sum / num_stud;
    }

    public static void main(String args[])
    {
        Catalog ca = new Catalog("244" , new Student("Pop Irina" , 9.5 , 8.3) , new Student("Avram Ion" , 6.5 , 7.4) ,
                new Student("Ionescu Mircea" , 9.5 , 7.5) , new Student("Pop Magda" , 6.5 , 8.0) , new Student("Tiganus Denisa" , 9 , 8));
        System.out.println("Media pe clasa este: " + ca.getMedieClasa());
        System.out.println(ca.toString());
    }
}
