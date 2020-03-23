import java.util.ArrayList;
import java.util.LinkedList;

public interface Writer {
    void WriteActiveSessionInFile(LinkedList<ParkingSession>activeParkingSessions);
    void WriteUsersInFile(ArrayList<User>users);
    void WriteEndedSessionInFile(LinkedList<ParkingSession>endedParkingSessions);
}
