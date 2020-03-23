import java.io.Serializable;
import java.util.Date;

public class ParkingSession implements Serializable {
    // Date and time of arriving at the parking
    private Date entryDt;
    // Date and time of payment for the parking
     private Date paymentDt;
    // Date and time of exiting the parking
    private Date exitDt;
    // Total cost of parking
    private int totalPayment;
    // Plate number of the visitor's car
    private String carPlateNumber;
    // Issued printed ticket
    private int ticketNumber;

    private User currentUser;


    public ParkingSession() {
        currentUser = new User();
    }

    public Date getEntryDt() {
        return entryDt;
    }

    public void setEntryDt(Date entryDt) {
        this.entryDt = entryDt;
    }

    public Date getPaymentDt() {
        return paymentDt;
    }

    public void setPaymentDt(Date paymentDt) {
        this.paymentDt = paymentDt;
    }

    public Date getExitDt() {
        return exitDt;
    }

    public void setExitDt(Date exitDt) {
        this.exitDt = exitDt;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCarPlateNumber()+" ");
        sb.append(getTicketNumber()+" ");
        sb.append(getEntryDt()+" ");
        sb.append(getTotalPayment()+" ");
        sb.append(getPaymentDt()+" ");
        sb.append(getExitDt()+" ");
        sb.append(getCurrentUser().toString());
        return sb.toString();
    }
}
