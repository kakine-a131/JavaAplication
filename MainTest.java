import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }

    @Test
    void isWeekday() throws ParseException {
        String inputDate = "2023-05-17";
        String dateFormat = "yyyy-MM-dd";
        boolean actual =  Main.isWeekday(inputDate,dateFormat);
        assertEquals(true,actual);
        String inputDate1 = "2023/05/20";
        String dateFormat1 = "yyyy/MM/dd";
        boolean actual1 =  Main.isWeekday(inputDate1,dateFormat1);
        assertEquals(false,actual1);

    }

    @org.junit.jupiter.api.Test
    void isHoliday() {
        String inputDate = "2023-05-17";
        String dateFormat = "yyyy-MM-dd";
        boolean actual =  Main.isHoliday(inputDate,dateFormat);
        assertEquals(false,actual);
        String inputDate1 = "2023/05/20";
        String dateFormat1 = "yyyy/MM/dd";
        boolean actual1 =  Main.isHoliday(inputDate1,dateFormat1);
        assertEquals(true,actual1);

    }
    @Test
    void countWorkingDays() throws ParseException {
        assertEquals(2,Main.countWorkingDays("2023/05/11","2023/05/12"));
        assertEquals(4,Main.countWorkingDays("2023/05/11","2023/05/16"));
        assertEquals(7,Main.countWorkingDays("2023/05/11","2023/05/20"));
        assertEquals(7,Main.countWorkingDays("2023/05/11","2023/05/21"));
    }

    @Test
    void getDaysBetweenDates() throws ParseException {
        Date date1_1 = Main.validateAndParseDate("2023/03/04");
        Date date1_2 = Main.validateAndParseDate("2023/03/06");
        assertEquals(2, Main.getDaysBetweenDates(date1_1, date1_2));
        Date date2_1 = Main.validateAndParseDate("2023/03/30");
        Date date2_2 = Main.validateAndParseDate("2023/04/01");
        assertEquals(2, Main.getDaysBetweenDates(date2_1, date2_2));
    }

    @Test
    void getNextWorkingDay() throws ParseException{
        assertEquals("2023/05/25",Main.getNextWorkingDay("2023/05/24"));
        assertEquals("2023/05/29",Main.getNextWorkingDay("2023/05/26"));
    }

//    @Test
//    void getNationalHoliday() throws Exception{
//        String[] expected = {
//                "2023/01/01",
//                "2023/01/02",
//                "2023/01/09",
//                "2023/02/11",
//                "2023/03/21",
//                "2023/04/29",
//                "2023/05/03",
//                "2023/05/04",
//                "2023/05/05",
//                "2023/07/17",
//                "2023/08/11",
//                "2023/09/18",
//                "2023/09/23",
//                "2023/10/09",
//                "2023/11/03",
//                "2023/11/23",
//        };
//        assertEquals(expected,Main.getNationalHoliday(2023));
//    }

}