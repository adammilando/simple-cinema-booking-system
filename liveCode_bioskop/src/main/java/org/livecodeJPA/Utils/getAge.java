package org.livecodeJPA.Utils;;
import java.util.Calendar;
import java.util.Date;

public class getAge {
    public static int age(Date birthDate, Date showDate){
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar show = Calendar.getInstance();
        show.setTime(showDate);

        int age = show.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if ( show.get(Calendar.MONTH)< birth.get(Calendar.MONTH) ||
                (show.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
                        show.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))){
            age--;
        }
        return age;
    }
}
