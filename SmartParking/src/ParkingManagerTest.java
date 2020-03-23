import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Date;
public class ParkingManagerTest {

    @Test
    public void tryLeaveParkingWithTicket() {

    }

    @Test
    public void getRemainingCost() {
        int expected = 100;
        ParkingManager pm = new ParkingManager(new TestLoader(), new TestWriter());
        pm.setParkingCapacity(200);
        Date in = new Date(2019,12,22,12,00);
        ApplicationHelper.getInstance().setCurrentDate(in);
        ParkingSession ps1 =  pm.EnterParking("123456");
        Date out = new Date(2019,12,22,14,00);
        ApplicationHelper.getInstance().setCurrentDate(out);
        int remainCost = pm.GetRemainingCost(ps1.getTicketNumber());
        assertEquals(expected,remainCost);
    }
}