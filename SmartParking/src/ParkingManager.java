import java.awt.desktop.AppForegroundListener;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

public class ParkingManager {

    private LinkedList<ParkingSession> activeParkingSessions;
    private LinkedList<ParkingSession> endedParkingSessions;
    private ArrayList<Tariff> tariffs;
    private ArrayList<User> users;
    private int parkingCapacity;
    private Writer writer;
    private Loader loader;


    public int getParkingCapacity()
    {
        return parkingCapacity;
    }

    public void setParkingCapacity(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public ParkingManager(Loader loader,Writer writer) {
        this.loader = loader;
        this.writer = writer;
        tariffs = loader.loadTariff();
        users = loader.loadUsers();
        activeParkingSessions = loader.loadActiveSessions();
        endedParkingSessions = loader.loadEndedSessions();

    }


    public int CreateTicketNumber()
    {
        Date dt = ApplicationHelper.getInstance().getCurrentDate();
        System.out.println("Created ticket number:");
        return (int)(dt.getTime()%10000000)+(int)(dt.getTime()%10);

    }
    public void addUser(User u)
    {
        users.add(u);
        writer.WriteUsersInFile(users);
    }

    /* BASIC PART */
    public ParkingSession EnterParking(String carPlateNumber)
    {
        boolean fl =  activeParkingSessions.stream().anyMatch(t-> t.getCarPlateNumber().equals(carPlateNumber));

        if (activeParkingSessions.size()<getParkingCapacity()&&!fl)
        {
            ParkingSession ps = new ParkingSession();
            ps.setCarPlateNumber(carPlateNumber);
            Date dt = ApplicationHelper.getInstance().getCurrentDate();
         //   DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");  //for representing datetime
            ps.setEntryDt(dt);


            ps.setTicketNumber(CreateTicketNumber());
            Optional<User> optionalUsr = users.stream().filter(t->t.getCarPlateNumber().equals(carPlateNumber)).findFirst();

            if (optionalUsr.isPresent())
            {
                ps.setCurrentUser(optionalUsr.get());
            }

            activeParkingSessions.add(ps);
            writer.WriteActiveSessionInFile(activeParkingSessions);
            return ps;
        }
        else
            return null;
       // throw new  UnsupportedOperationException();
    }

    public boolean TryLeaveParkingWithTicket(int ticketNumber,  ParkingSession session)
    {
        Date dt = ApplicationHelper.getInstance().getCurrentDate();
        int delt=0;

        if (session.getPaymentDt()==null) {
             delt = (int) (dt.getTime() - session.getEntryDt().getTime());
        }
        else
        {
            delt = (int) (dt.getTime() - session.getPaymentDt().getTime());
        }
        int  cur = (delt % 3600000) / 60000;


        if (cur > tariffs.get(0).minutes) {
            return false;
        }

        session.setExitDt(new Date());
        endedParkingSessions.add(session);
        activeParkingSessions.remove(session);
        writer.WriteActiveSessionInFile(activeParkingSessions);
        writer.WriteEndedSessionInFile(endedParkingSessions);
        return true;
        /*
         * Check that the car leaves parking within the free leave period
         * from the PaymentDt (or if there was no payment made, from the EntryDt)
         * 1. If yes:
         *   1.1 Complete the parking session by setting the ExitDt property
         *   1.2 Move the session from the list of active sessions to the list of past sessions             *
         *   1.3 return true and the completed parking session object in the out parameter
         *
         * 2. Otherwise, return false, session = null
         */
        //throw new UnsupportedOperationException();
    }

    public int GetRemainingCost(int ticketNumber)
    {
        int currentAmount=0;
        Date dt = ApplicationHelper.getInstance().getCurrentDate();
       Optional<ParkingSession> optionalParkingSession = activeParkingSessions.stream().filter(t->t.getTicketNumber()==ticketNumber).findFirst();
if(!optionalParkingSession.isPresent())
{
    throw new IllegalArgumentException();//NotFoundSession
}
                currentAmount = optionalParkingSession.get().getTotalPayment();
                int delt = (int) (dt.getTime()-optionalParkingSession.get().getEntryDt().getTime());// get pasted time from entry point
                int cur =(delt / 60000) ;// get current time in minutes
            if (tariffs.get(tariffs.size()-1).minutes <= cur)
            {
                return tariffs.get(tariffs.size()-1).rate-currentAmount;//returning remaining amount
            }
            else {
                for (int i = 0; i < tariffs.size(); i++) {
                    if (tariffs.get(i).minutes >= cur) {
                        return tariffs.get(i).rate - currentAmount;//returning remaining amount
                    }
                }
            }

        throw new  UnsupportedOperationException();
    }

    public void PayForParking(int ticketNumber, int amount)
    {
        activeParkingSessions.stream().filter(t->t.getTicketNumber()==ticketNumber).findFirst().ifPresent(t->setPayment(t,amount));
    }
    private void setPayment(ParkingSession ps, int amount)
    {
        ps.setPaymentDt(new Date());
        if(ps.getTotalPayment()==0)
            ps.setTotalPayment(amount);
        else
            ps.setTotalPayment(ps.getTotalPayment()+amount);
    }

    /* ADDITIONAL TASK 2 */
    public boolean TryLeaveParkingByCarPlateNumber(String carPlateNumber, ParkingSession session)
    {
        if(TryLeaveParkingWithTicket(session.getTicketNumber(), session))//Free period (15 min)
        {
            return true;
        }
        else//more than 15 min left
        {
            Date dt = ApplicationHelper.getInstance().getCurrentDate();//current time
            int delt = (int) (dt.getTime()-session.getPaymentDt().getTime());// get pasted time payment
            int cur =(delt % 3600000) / 60000;// get current time in minutes

            if (session.getPaymentDt()!=null)//session is paid
            {
                if(cur<=15) {//Free Period from Paying for parking
                    session.setExitDt(ApplicationHelper.getInstance().getCurrentDate());
                    endedParkingSessions.add(session);
                    activeParkingSessions.remove(session);
                    writer.WriteActiveSessionInFile(activeParkingSessions);
                    writer.WriteEndedSessionInFile(endedParkingSessions);
                    return true;
                }
                else if( GetRemainingCost(session.getTicketNumber())==0)//Paid for more minutes
                {
                    return true;
                }
                   else return false;//Additional Payment needed

            }
            else//The user has not paid for the parking session:
            {
                if (session.getCurrentUser()!=null)//loyalty system for users
                {
                    Date d = ApplicationHelper.getInstance().getCurrentDate();
                    session.setPaymentDt(d);
                  session.setExitDt(d);
                  int ticket = session.getTicketNumber();
                 PayForParking(ticket,GetRemainingCost(ticket));//paying for ticket
                }
                else
                {
                    session = null;
                    return false;
                }
            }

        }

        return false;


        //throw new  UnsupportedOperationException();
    }
public void printActiveSessions(){

        for (ParkingSession ps: activeParkingSessions)
            System.out.println(ps.toString());
}
public void printEndedSessions(){
    for (ParkingSession ps: endedParkingSessions)
        System.out.println(ps.toString());
    }


}
