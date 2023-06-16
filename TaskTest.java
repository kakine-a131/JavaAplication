import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task = new Task();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isExpired() throws Exception{
        task.deadline = "2023/05/02";
        assertEquals(true,task.isExpired());
    }

    @Test
    void getDaysLeft() throws ParseException {
        task.deadline = "2023/06/11";
        assertEquals(6,task.getDaysLeft());
    }

    @Test
    void getWorkingDaysLeft() throws  ParseException{
        task.deadline = "2023/06/11";
        assertEquals(4,task.getWorkingDaysLeft());
    }

    @Test
    void isToday() throws ParseException{
        task.l_deadline = LocalDate.of(2023,6,6);
        assertEquals(false,task.isToday());
    }

}