package business.shipitnow.com.coman;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLib {






    public static String convertDate(String date,String format) {

        // String date = "Mar 10, 2016 6:30:00 PM";
        Date newDate = null;
        try {
            SimpleDateFormat InputDat = new SimpleDateFormat(format);
            newDate = InputDat.parse(date);
            InputDat = new SimpleDateFormat("dd MMM yyyy");
            date = InputDat.format(newDate);
            return date;
        } catch (ParseException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("QWE","convertDate Exception "+e.getMessage());
            return date;
        }
    }
}
