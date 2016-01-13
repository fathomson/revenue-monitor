package sa.revenue.advertisers.tapjoy.model.api;

import java.util.List;



public class Day {
    private String Date;
    private String Timezone;
    private int TotalOffers;
    private int PageSize;
    private int TotalPages;
    private int CurrentPage;
    private List<App> Apps;

    public Day() {
    }

    public Day(String date, String timezone, int totalOffers, int pageSize, int totalPages, int currentPage, List<App> apps) {
        Date = date;
        Timezone = timezone;
        TotalOffers = totalOffers;
        PageSize = pageSize;
        TotalPages = totalPages;
        CurrentPage = currentPage;
        Apps = apps;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        Timezone = timezone;
    }

    public int getTotalOffers() {
        return TotalOffers;
    }

    public void setTotalOffers(int totalOffers) {
        TotalOffers = totalOffers;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    public List<App> getApps() {
        return Apps;
    }

    public void setApps(List<App> apps) {
        Apps = apps;
    }
}
