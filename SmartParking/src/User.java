import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String carPlateNumber;
    public String phone;

    public User() {

    }
    public User(String name, String carPlateNumber, String phone) {
        this.name = name;
        this.carPlateNumber = carPlateNumber;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()+" ");
        sb.append(getPhone()+" ");
        return sb.toString();
    }
}
