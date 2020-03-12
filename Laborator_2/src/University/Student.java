package University;

public class Student {
    private String nume;
    private String prenume;
    public static int contor;

    static
    {
        contor = 0;
    }
    public Student()
    {
        nume = "";
        prenume = "";
        contor ++;
    }
    public Student(String num , String pren)
    {
        nume = num;
        prenume = pren;
        contor ++;
    }
    public void infoStudent()
    {
        System.out.println("Nume: " + nume + " Prenume: " + prenume);
    }

    public static void main(String args[])
    {
        Student s1 = new Student("Popescu" , "Mircea");
        Student s2 = new Student("Milea" , "Catalina");
        Student s3 = new Student("Ionescu" , "Eliza");
        Student s4 = new Student("Popa" , "Maria");
        Student s5 = new Student("King" , "Tessa");
        Student s[] = {s1 , s2 , s3 , s4 , s5};
        for(Student elem : s)
        {
            elem.infoStudent();
        }
        System.out.println("Numarul de studenti este: " + Student.contor);
    }

}
