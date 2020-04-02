package Model;

import java.util.Scanner;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {

        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {

        return month;
    }

    public void setMonth(int month) {

        this.month = month;
    }

    public int getYear() {

        return year;
    }

    public void setYear(int year) {

        this.year = year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Date)) {
            return false;
        }
        Date d = (Date) o;

        // Compare the data members and return accordingly
        return day == d.getDay() && month == d.getMonth() && year == d.getYear();
    }

}
