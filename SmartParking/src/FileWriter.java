import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileWriter implements Writer {
    @Override
    public void WriteActiveSessionInFile(LinkedList<ParkingSession> activeParkingSessions) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("activeSessions.dat")))
        {
            oos.writeObject(activeParkingSessions);
            System.out.println("File activeSessions.dat has been written");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void WriteUsersInFile(ArrayList<User>users) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat")))
        {
            oos.writeObject(users);
            System.out.println("File users.dat has been written");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void WriteEndedSessionInFile(LinkedList<ParkingSession> endedParkingSessions) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("endedSessions.dat")))
        {
            oos.writeObject(endedParkingSessions);
            System.out.println("File endedSessions.dat has been written");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
