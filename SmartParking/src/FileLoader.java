import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileLoader implements Loader {

    @Override
    public ArrayList<Tariff> loadTariff() {
        ArrayList<Tariff> tariffs = new ArrayList<Tariff>(11);
        int time =60;
        int rate= 80;
        for (int i = 0; i<11; i++)//setting tariffs array
        {
            Tariff t = new Tariff();

            if (i==0)
            {
                t.minutes = 15;
                t.rate = 0;
            }
            else{
                t.minutes=time;
                time+=60;
                t.rate=rate;
                if (i<3)
                    rate+=50;
                else if(i<=8)
                    rate+=40;
                else rate+=0;

            }
            tariffs.add(t);
        }
        return tariffs;
    }

    @Override
    public ArrayList<User> loadUsers() {
        ArrayList<User> users =new ArrayList<User>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat")))
        {
            users= (ArrayList<User>) ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public  LinkedList<ParkingSession> loadActiveSessions() {
        LinkedList<ParkingSession> activeParkingSessions=new LinkedList<ParkingSession>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("activeSessions.dat")))
        {
            activeParkingSessions= (LinkedList<ParkingSession>) ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return activeParkingSessions;
    }

    @Override
    public  LinkedList<ParkingSession> loadEndedSessions() {
        LinkedList<ParkingSession> endedParkingSessions=new LinkedList<ParkingSession>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("endedSessions.dat")))
        {
            endedParkingSessions= (LinkedList<ParkingSession>) ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return endedParkingSessions;
    }

}
