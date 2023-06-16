import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task {
    String deadline;
    LocalDate l_deadline;
    int actualTime;
    int schedule;
    Status status;
    public  Task(){
        this.deadline = deadline;
        this.actualTime = 0;
        this.status = Status.UNHANDLED;

    }
    public boolean isExpired() throws ParseException {
        Date today = new Date();
        Date d_deadline = DateUtil.validateAndParseDate(deadline);
        return today.after(d_deadline);
    }

    public int getDaysLeft() throws ParseException {
        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String s_today = sdf.format(cl.getTime());
        Date d_deadline = DateUtil.validateAndParseDate(deadline);
        Date d_today = DateUtil.validateAndParseDate(s_today);
        int result = DateUtil.getDaysBetweenDates(d_today,d_deadline);
        return result;
    }

    public boolean isToday() throws ParseException {
        LocalDate today = LocalDate.now();
        if(DateUtil.getDaysBetweenDays(l_deadline,today) == 0){
            return true;
        }
        return false;
    }

    public int getWorkingDaysLeft() throws ParseException{
        Date today = new Date();
        Date d_deadline = DateUtil.validateAndParseDate(deadline);
        return DateUtil.countWorkingDays(today,d_deadline);
    }

    public int getActualTime(){
        return actualTime;
    }

    public int getSumOfActualTime(Task[] tasks){
        int sum = 0;
        for(Task task:tasks){
            sum += task.getActualTime();
        }
        return sum;
    }

    public enum Status{
        UNHANDLED("未対応"),
        PROCESSING("処理中"),
        DONE("完了"),
        UNDER_VERIFICATION("検討中");

        private final String displayValue;
        Status(String displayValue){
            this.displayValue = displayValue;
        }

    }

}
