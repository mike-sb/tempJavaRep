import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.LinkedList;

public interface Loader {
    ArrayList<Tariff> loadTariff();
    ArrayList<User> loadUsers();
    LinkedList<ParkingSession> loadActiveSessions();
    LinkedList<ParkingSession> loadEndedSessions();
}
