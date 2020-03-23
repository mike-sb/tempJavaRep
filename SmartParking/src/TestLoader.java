import java.util.ArrayList;
import java.util.LinkedList;

public class TestLoader implements Loader {
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
        return null;
    }

    @Override
    public  LinkedList<ParkingSession> loadActiveSessions() {
        return null;
    }

    @Override
    public LinkedList<ParkingSession> loadEndedSessions() {
        return null;
    }
}
