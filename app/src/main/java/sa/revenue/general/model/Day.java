package sa.revenue.general.model;

/**
 * Created by un on 1/9/2016.
 */
public class Day {
    String date;
    double admob;
    double tapjoy;

    public Day() {
    }

    public Day(String date, double admob, double tapjoy) {
        this.date = date;
        this.admob = admob;
        this.tapjoy = tapjoy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAdmob() {
        return admob;
    }

    public void setAdmob(double admob) {
        this.admob = admob;
    }

    public double getTapjoy() {
        return tapjoy;
    }

    public void setTapjoy(double tapjoy) {
        this.tapjoy = tapjoy;
    }
}
