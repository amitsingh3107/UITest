package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static  String setDateMonthBase(int month){
       return setDateMonthDayBased(month,0);

    }
    public static String setDateMonthDayBased(int month, int days){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate  futureDate = LocalDate.now().plusMonths(month).plusDays(days);
        return formatter.format(futureDate).toString();


    }
}
