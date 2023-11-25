package Models;

import Models.Interfaces.IComaprable;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Student implements IComaprable {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate birthDate;
    private final String address;
    private final String phoneNumber;
    private final String faculty;
    private final int course;
    private final String group;

    public Student(int id, String lastName, String firstName, String middleName, String birthDate,
                   String address, String phoneNumber, String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = LocalDate.parse(birthDate);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    @Override
    public boolean compareFaculties(String faculty) {
        return this.faculty.equals(faculty);
    }

    @Override
    public boolean compareGroups(String group) {
        return this.group.equals(group);
    }

    @Override
    public boolean compareDates(LocalDate localDate) {
        return this.birthDate.isAfter(localDate);
    }

    @Override
    public String toString() {
        return id + " " + lastName + " " + firstName + " " + middleName + " " + birthDate + " " +
                address + " " + phoneNumber + " " + faculty + " " + course + " " + group;
    }
}
