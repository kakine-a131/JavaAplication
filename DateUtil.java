import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static boolean isWeekday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;
    }

    public static boolean isWeekday(String dateString,String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isHoliday(String dateString,String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getNationalHoliday(int year){
        List<String> holidays = new ArrayList<>();
        try{
            String apiUrl = "https://holidays-jp.github.io/api/v1/date.json?year=" + year;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // JSONレスポンスを解析して祝日の配列を取得
                holidays = parseHolidaysFromJson(response.toString());
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return holidays;
    }

    private static List<String> parseHolidaysFromJson(String jsonResponse) {
        List<String> holidays = new ArrayList<>();
        return  holidays;
    }

    public static int countWorkingDays(String from,String to) throws ParseException {
        Date d_from = validateAndParseDate(from);
        Date d_to = validateAndParseDate(to);
        int diffDays = getDaysBetweenDates(d_from,d_to);
        int count = 0;
        for(int i=0; i<=diffDays; i++){
            Date date = addDaysToDate(d_from,i);
            if(isWeekday(date)){
                count++;
            }
        }
        return count;
    }
    public static int countWorkingDays(Date from,Date to) throws ParseException {
        int diffDays = getDaysBetweenDates(from,to);
        int count = 0;
        for(int i=1; i<=diffDays; i++){
            Date date = addDaysToDate(from,i);
            if(isWeekday(date)){
                count++;
            }
        }
        return count;
    }

    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static String getNextWorkingDay(String date) throws ParseException {
        Date d_date = validateAndParseDate(date);
        Date next = addDaysToDate(d_date,1);
        while(!isWeekday(next)){
            next = addDaysToDate(next,1);
        }
        String s_next = new SimpleDateFormat("yyyy/MM/dd").format(next);
        return s_next;
    }

    public static int getDaysBetweenDates(Date date1,Date date2){
        long datetime1 = date1.getTime();
        long datetime2 = date2.getTime();
        long one_date_time = 1000 * 60 * 60 * 24;
        long diffDays = (datetime1 - datetime2) / one_date_time;
        if(diffDays<0){
            diffDays = diffDays*(-1);
        }
        return (int)diffDays;
    }

    public static  long getDaysBetweenDays(LocalDate date1,LocalDate date2){
        return ChronoUnit.DAYS.between(date1,date2);
    }

    public static Date validateAndParseDate(String inputDate) throws ParseException {
        if (!inputDate.matches("^\\d{4}/\\d{2}/\\d{2}$")) {
            throw new ParseException("Invalid date format", 0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);

        return sdf.parse(inputDate);
    }

}
