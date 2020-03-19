package exercise_1;

public class StringEx {
    private String s;
    public StringEx(String s)
    {
        this.s = s;
    }
    public char maxOccuringChar()
    {
        int frecv[] = new int['z' - 'a' + 1];

       s = s.toLowerCase();
       int max = -1;
       char maxChr = ' ';
        for(char chr : s.toCharArray())
        {
            frecv[chr - 'a']++;
            if(frecv[chr - 'a'] > max)
            {
                max = frecv[chr - 'a'];
                maxChr = chr;
            }
        }
        return maxChr;
    }

    public String concatenate(String s1)
    {
        String str1 = s.substring(0 , 3);
        String str2 = s1.substring(s1.length() -  3 , s1.length());
        if(str1.equals(str2)) //de ce nu functiona str1 == str2?
        {
            return s.concat(s1);
        }
        return s1.concat(s);
    }
    public static void main(String args[])
    {
        String s1 = "         Hello! Today is a good good day";
        String s2 = "good";
        String s3 = "no";
        //indexOf
        System.out.println("indexOf");
        System.out.println("The first occurrence of " + s2 + " in s1 is at index " + s1.indexOf(s2));
        System.out.println("The first occurrence of " + s3 + " in s1 is at index " + s1.indexOf(s3) + " so there is no occurrence");
        System.out.println("The first occurrence of a in s1 is at index " + s1.indexOf('a'));
        //lastIndexOf
        System.out.println("lastIndexOf");
        System.out.println("The last occurrence of " + s2 + " in s1 is at index " + s1.lastIndexOf(s2));
        System.out.println("The last occurrence of a in s1 is at index " + s1.lastIndexOf('a'));
        //length
        System.out.println("Length of " + s1 + ":" + s1.length());
        System.out.println("Length of " + s2 + ":" + s2.length());
        System.out.println("Length of hello:" + "hello".length()); //nu ia in calcul \n
        System.out.println("Length of a:" + "a".length());
        //split
        System.out.println("Split:");
        String[] arr1 = s1.split(" " , 0);
        System.out.println("Regex:  , Limit: 0");
        for(String str : arr1)
            System.out.print(str + "|");
        String[] arr2 = s1.split("o" , 3);
        System.out.println("\nRegex:o , Limit: 3");
        for(String str : arr2)
            System.out.print(str + "|");
        System.out.println();
        //maximum occurring character
        StringEx str = new StringEx("banananun");
        System.out.println(str.maxOccuringChar());
        //replace
        System.out.println("Replace character o with b: " + s1.replace('o' , 'b'));
        //concatenate
        System.out.println("Concatenate:" + str.concatenate("labanl"));
        //remove spaces
        System.out.println("Remove spaces from s1: " + s1.replaceAll("\\s",""));
        //trim
        System.out.println("Trim s1: " + s1.trim());
    }
}
