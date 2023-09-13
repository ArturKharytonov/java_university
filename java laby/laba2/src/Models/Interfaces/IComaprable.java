package Models.Interfaces;

import java.time.LocalDate;

public interface IComaprable {
    boolean compareFaculties(String faculty);
    boolean compareGroups(String group);
    boolean compareDates(LocalDate localDate);
}
