package sa.revenue.general.model;

import java.util.List;

import sa.revenue.general.db.AdPlacement;


/**
 * Created by un on 1/3/2016.
 */
public class RevenueModel {
    String totalRevenue;
    String admobRevenue;
    List<AdPlacement> admobAdPlacements;

    String tapjoyRevenue;
    List<AdPlacement> tapjoyAdPlacements;

    public RevenueModel() {
    }

    public RevenueModel(String totalRevenue, String admobRevenue, List<AdPlacement> admobAdPlacements, String tapjoyRevenue, List<AdPlacement> tapjoyAdPlacements) {
        this.totalRevenue = totalRevenue;
        this.admobRevenue = admobRevenue;
        this.admobAdPlacements = admobAdPlacements;
        this.tapjoyRevenue = tapjoyRevenue;
        this.tapjoyAdPlacements = tapjoyAdPlacements;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getAdmobRevenue() {
        return admobRevenue;
    }

    public void setAdmobRevenue(String admobRevenue) {
        this.admobRevenue = admobRevenue;
    }

    public List<AdPlacement> getAdmobAdPlacements() {
        return admobAdPlacements;
    }

    public void setAdmobAdPlacements(List<AdPlacement> admobAdPlacements) {
        this.admobAdPlacements = admobAdPlacements;
    }

    public String getTapjoyRevenue() {
        return tapjoyRevenue;
    }

    public void setTapjoyRevenue(String tapjoyRevenue) {
        this.tapjoyRevenue = tapjoyRevenue;
    }

    public List<AdPlacement> getTapjoyAdPlacements() {
        return tapjoyAdPlacements;
    }

    public void setTapjoyAdPlacements(List<AdPlacement> tapjoyAdPlacements) {
        this.tapjoyAdPlacements = tapjoyAdPlacements;
    }
}
