import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Welcome to our Smart parking application");
        ParkingManager pm = new ParkingManager();
        pm.setParkingCapacity(200);

        System.out.println("Active: ");
        System.out.println("CarNumber  Ticket Enter__Date  Cost Payment_Date Leaving_date User_name User_phone");
        pm.printActiveSessions();
        System.out.println("Ended: ");
        System.out.println("CarNumber  Ticket           Enter__Date        Cost Payment_Date Leaving_date User_name User_phone");
        pm.printEndedSessions();

//
//        User u1 = new User("Mike","rt123p70rus", "88005553535");
//        User u2 = new User("John","ee228i99rus","80209394911");
//        User u3 = new User("Nick","oo009r10rus","89991914959");
//pm.addUser(u1);
//pm.addUser(u2);
//pm.addUser(u3);

//        ParkingSession ps1 = pm.EnterParking("rt123p70rus");
//        ParkingSession ps2 = pm.EnterParking("ee228i99rus");
//        ParkingSession ps3 = pm.EnterParking("oo009r10rus");
//
//        if (pm.TryLeaveParkingWithTicket(ps2.getTicketNumber(),ps2))
//        {
//            System.out.println("User 2 leaved");
//        }


    }
}
