import java.util.Date;

public class ApplicationHelper {

    private static ApplicationHelper instance;
    private Date currentDate;
    private boolean modeTest = false;

    public static ApplicationHelper getInstance()
    {
        if (instance == null)
            instance = new ApplicationHelper();

        return instance;
    }

    private ApplicationHelper() {
    }


    public Date getCurrentDate() {
        if (!modeTest)
            currentDate = new Date();
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
        modeTest = true;
    }

}
