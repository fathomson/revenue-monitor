package sa.revenue.advertisers.tapjoy.model.api;


public class App {
    private String Name;
    private String AppStoreID;
    private String AppName;
    private String AppKey;
    private String url;
    private String Platform;
    private boolean Rewarded;
    private String OfferType;
    private int PaidInstalls;
    private int[] PaidInstallsHourly;
    private int PaidClicks;
    private int[] PaidClicksHourly;
    private double Spend;
    private double[] SpendHourly;
    private int Sessions;
    private int[] SessionsHourly;
    private int NewUsers;
    private int[] NewUsersHourly;
    private int DailyActiveUsers;
    private double OfferwallRevenue;
    private double[] OfferwallRevenueHourly;
    private int OfferwallImpressions;
    private int[] OfferwallImpressionsHourly;
    private int OfferwallClicks;
    private int[] OfferwallClicksHourly;
    private int OfferwallConversions;
    private int[] OfferwallConversionsHourly;
    private double FeaturedOfferRevenue;
    private double[] FeaturedOfferRevenueHourly;
    private int FeaturedOfferImpressions;
    private int[] FeaturedOfferImpressionsHourly;
    private int FeaturedOfferClicks;
    private int[] FeaturedOfferClicksHourly;
    private int FeaturedOfferConversions;
    private int[] FeaturedOfferConversionsHourly;
    private double TJMOfferwallRevenue;
    private double[] TJMOfferwallRevenueHourly;
    private int TJMOfferwallImpressions;
    private int[] TJMOfferwallImpressionsHourly;
    private int TJMOfferwallClicks;
    private int[] TJMOfferwallClicksHourly;
    private int TJMOfferwallConversions;
    private int[] TJMOfferwallConversionsHourly;
    private double DirectPlayRevenue;
    private double[] DirectPlayRevenueHourly;
    private int DirectPlayImpressions;
    private int[] DirectPlayImpressionsHourly;
    private int DirectPlayClicks;
    private int[] DirectPlayClicksHourly;
    private int DirectPlayConversions;
    private int[] DirectPlayConversionsHourly;


    public App() {
    }

    public App(String name, String appStoreID, String appName, String appKey, String url, String platform, boolean rewarded, String offerType, int paidInstalls, int[] paidInstallsHourly, int paidClicks, int[] paidClicksHourly, double spend, double[] spendHourly, int sessions, int[] sessionsHourly, int newUsers, int[] newUsersHourly, int dailyActiveUsers, double offerwallRevenue, double[] offerwallRevenueHourly, int offerwallImpressions, int[] offerwallImpressionsHourly, int offerwallClicks, int[] offerwallClicksHourly, int offerwallConversions, int[] offerwallConversionsHourly, double featuredOfferRevenue, double[] featuredOfferRevenueHourly, int featuredOfferImpressions, int[] featuredOfferImpressionsHourly, int featuredOfferClicks, int[] featuredOfferClicksHourly, int featuredOfferConversions, int[] featuredOfferConversionsHourly, double TJMOfferwallRevenue, double[] TJMOfferwallRevenueHourly, int TJMOfferwallImpressions, int[] TJMOfferwallImpressionsHourly, int TJMOfferwallClicks, int[] TJMOfferwallClicksHourly, int TJMOfferwallConversions, int[] TJMOfferwallConversionsHourly, double directPlayRevenue, double[] directPlayRevenueHourly, int directPlayImpressions, int[] directPlayImpressionsHourly, int directPlayClicks, int[] directPlayClicksHourly, int directPlayConversions, int[] directPlayConversionsHourly) {
        Name = name;
        AppStoreID = appStoreID;
        AppName = appName;
        AppKey = appKey;
        this.url = url;
        Platform = platform;
        Rewarded = rewarded;
        OfferType = offerType;
        PaidInstalls = paidInstalls;
        PaidInstallsHourly = paidInstallsHourly;
        PaidClicks = paidClicks;
        PaidClicksHourly = paidClicksHourly;
        Spend = spend;
        SpendHourly = spendHourly;
        Sessions = sessions;
        SessionsHourly = sessionsHourly;
        NewUsers = newUsers;
        NewUsersHourly = newUsersHourly;
        DailyActiveUsers = dailyActiveUsers;
        OfferwallRevenue = offerwallRevenue;
        OfferwallRevenueHourly = offerwallRevenueHourly;
        OfferwallImpressions = offerwallImpressions;
        OfferwallImpressionsHourly = offerwallImpressionsHourly;
        OfferwallClicks = offerwallClicks;
        OfferwallClicksHourly = offerwallClicksHourly;
        OfferwallConversions = offerwallConversions;
        OfferwallConversionsHourly = offerwallConversionsHourly;
        FeaturedOfferRevenue = featuredOfferRevenue;
        FeaturedOfferRevenueHourly = featuredOfferRevenueHourly;
        FeaturedOfferImpressions = featuredOfferImpressions;
        FeaturedOfferImpressionsHourly = featuredOfferImpressionsHourly;
        FeaturedOfferClicks = featuredOfferClicks;
        FeaturedOfferClicksHourly = featuredOfferClicksHourly;
        FeaturedOfferConversions = featuredOfferConversions;
        FeaturedOfferConversionsHourly = featuredOfferConversionsHourly;
        this.TJMOfferwallRevenue = TJMOfferwallRevenue;
        this.TJMOfferwallRevenueHourly = TJMOfferwallRevenueHourly;
        this.TJMOfferwallImpressions = TJMOfferwallImpressions;
        this.TJMOfferwallImpressionsHourly = TJMOfferwallImpressionsHourly;
        this.TJMOfferwallClicks = TJMOfferwallClicks;
        this.TJMOfferwallClicksHourly = TJMOfferwallClicksHourly;
        this.TJMOfferwallConversions = TJMOfferwallConversions;
        this.TJMOfferwallConversionsHourly = TJMOfferwallConversionsHourly;
        DirectPlayRevenue = directPlayRevenue;
        DirectPlayRevenueHourly = directPlayRevenueHourly;
        DirectPlayImpressions = directPlayImpressions;
        DirectPlayImpressionsHourly = directPlayImpressionsHourly;
        DirectPlayClicks = directPlayClicks;
        DirectPlayClicksHourly = directPlayClicksHourly;
        DirectPlayConversions = directPlayConversions;
        DirectPlayConversionsHourly = directPlayConversionsHourly;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAppStoreID() {
        return AppStoreID;
    }

    public void setAppStoreID(String appStoreID) {
        AppStoreID = appStoreID;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public boolean isRewarded() {
        return Rewarded;
    }

    public void setRewarded(boolean rewarded) {
        Rewarded = rewarded;
    }

    public String getOfferType() {
        return OfferType;
    }

    public void setOfferType(String offerType) {
        OfferType = offerType;
    }

    public int getPaidInstalls() {
        return PaidInstalls;
    }

    public void setPaidInstalls(int paidInstalls) {
        PaidInstalls = paidInstalls;
    }

    public int[] getPaidInstallsHourly() {
        return PaidInstallsHourly;
    }

    public void setPaidInstallsHourly(int[] paidInstallsHourly) {
        PaidInstallsHourly = paidInstallsHourly;
    }

    public int getPaidClicks() {
        return PaidClicks;
    }

    public void setPaidClicks(int paidClicks) {
        PaidClicks = paidClicks;
    }

    public int[] getPaidClicksHourly() {
        return PaidClicksHourly;
    }

    public void setPaidClicksHourly(int[] paidClicksHourly) {
        PaidClicksHourly = paidClicksHourly;
    }

    public double getSpend() {
        return Spend;
    }

    public void setSpend(double spend) {
        Spend = spend;
    }

    public double[] getSpendHourly() {
        return SpendHourly;
    }

    public void setSpendHourly(double[] spendHourly) {
        SpendHourly = spendHourly;
    }

    public int getSessions() {
        return Sessions;
    }

    public void setSessions(int sessions) {
        Sessions = sessions;
    }

    public int[] getSessionsHourly() {
        return SessionsHourly;
    }

    public void setSessionsHourly(int[] sessionsHourly) {
        SessionsHourly = sessionsHourly;
    }

    public int getNewUsers() {
        return NewUsers;
    }

    public void setNewUsers(int newUsers) {
        NewUsers = newUsers;
    }

    public int[] getNewUsersHourly() {
        return NewUsersHourly;
    }

    public void setNewUsersHourly(int[] newUsersHourly) {
        NewUsersHourly = newUsersHourly;
    }

    public int getDailyActiveUsers() {
        return DailyActiveUsers;
    }

    public void setDailyActiveUsers(int dailyActiveUsers) {
        DailyActiveUsers = dailyActiveUsers;
    }

    public double getOfferwallRevenue() {
        return OfferwallRevenue;
    }

    public void setOfferwallRevenue(double offerwallRevenue) {
        OfferwallRevenue = offerwallRevenue;
    }

    public double[] getOfferwallRevenueHourly() {
        return OfferwallRevenueHourly;
    }

    public void setOfferwallRevenueHourly(double[] offerwallRevenueHourly) {
        OfferwallRevenueHourly = offerwallRevenueHourly;
    }

    public int getOfferwallImpressions() {
        return OfferwallImpressions;
    }

    public void setOfferwallImpressions(int offerwallImpressions) {
        OfferwallImpressions = offerwallImpressions;
    }

    public int[] getOfferwallImpressionsHourly() {
        return OfferwallImpressionsHourly;
    }

    public void setOfferwallImpressionsHourly(int[] offerwallImpressionsHourly) {
        OfferwallImpressionsHourly = offerwallImpressionsHourly;
    }

    public int getOfferwallClicks() {
        return OfferwallClicks;
    }

    public void setOfferwallClicks(int offerwallClicks) {
        OfferwallClicks = offerwallClicks;
    }

    public int[] getOfferwallClicksHourly() {
        return OfferwallClicksHourly;
    }

    public void setOfferwallClicksHourly(int[] offerwallClicksHourly) {
        OfferwallClicksHourly = offerwallClicksHourly;
    }

    public int getOfferwallConversions() {
        return OfferwallConversions;
    }

    public void setOfferwallConversions(int offerwallConversions) {
        OfferwallConversions = offerwallConversions;
    }

    public int[] getOfferwallConversionsHourly() {
        return OfferwallConversionsHourly;
    }

    public void setOfferwallConversionsHourly(int[] offerwallConversionsHourly) {
        OfferwallConversionsHourly = offerwallConversionsHourly;
    }

    public double getFeaturedOfferRevenue() {
        return FeaturedOfferRevenue;
    }

    public void setFeaturedOfferRevenue(double featuredOfferRevenue) {
        FeaturedOfferRevenue = featuredOfferRevenue;
    }

    public double[] getFeaturedOfferRevenueHourly() {
        return FeaturedOfferRevenueHourly;
    }

    public void setFeaturedOfferRevenueHourly(double[] featuredOfferRevenueHourly) {
        FeaturedOfferRevenueHourly = featuredOfferRevenueHourly;
    }

    public int getFeaturedOfferImpressions() {
        return FeaturedOfferImpressions;
    }

    public void setFeaturedOfferImpressions(int featuredOfferImpressions) {
        FeaturedOfferImpressions = featuredOfferImpressions;
    }

    public int[] getFeaturedOfferImpressionsHourly() {
        return FeaturedOfferImpressionsHourly;
    }

    public void setFeaturedOfferImpressionsHourly(int[] featuredOfferImpressionsHourly) {
        FeaturedOfferImpressionsHourly = featuredOfferImpressionsHourly;
    }

    public int getFeaturedOfferClicks() {
        return FeaturedOfferClicks;
    }

    public void setFeaturedOfferClicks(int featuredOfferClicks) {
        FeaturedOfferClicks = featuredOfferClicks;
    }

    public int[] getFeaturedOfferClicksHourly() {
        return FeaturedOfferClicksHourly;
    }

    public void setFeaturedOfferClicksHourly(int[] featuredOfferClicksHourly) {
        FeaturedOfferClicksHourly = featuredOfferClicksHourly;
    }

    public int getFeaturedOfferConversions() {
        return FeaturedOfferConversions;
    }

    public void setFeaturedOfferConversions(int featuredOfferConversions) {
        FeaturedOfferConversions = featuredOfferConversions;
    }

    public int[] getFeaturedOfferConversionsHourly() {
        return FeaturedOfferConversionsHourly;
    }

    public void setFeaturedOfferConversionsHourly(int[] featuredOfferConversionsHourly) {
        FeaturedOfferConversionsHourly = featuredOfferConversionsHourly;
    }

    public double getTJMOfferwallRevenue() {
        return TJMOfferwallRevenue;
    }

    public void setTJMOfferwallRevenue(double TJMOfferwallRevenue) {
        this.TJMOfferwallRevenue = TJMOfferwallRevenue;
    }

    public double[] getTJMOfferwallRevenueHourly() {
        return TJMOfferwallRevenueHourly;
    }

    public void setTJMOfferwallRevenueHourly(double[] TJMOfferwallRevenueHourly) {
        this.TJMOfferwallRevenueHourly = TJMOfferwallRevenueHourly;
    }

    public int getTJMOfferwallImpressions() {
        return TJMOfferwallImpressions;
    }

    public void setTJMOfferwallImpressions(int TJMOfferwallImpressions) {
        this.TJMOfferwallImpressions = TJMOfferwallImpressions;
    }

    public int[] getTJMOfferwallImpressionsHourly() {
        return TJMOfferwallImpressionsHourly;
    }

    public void setTJMOfferwallImpressionsHourly(int[] TJMOfferwallImpressionsHourly) {
        this.TJMOfferwallImpressionsHourly = TJMOfferwallImpressionsHourly;
    }

    public int getTJMOfferwallClicks() {
        return TJMOfferwallClicks;
    }

    public void setTJMOfferwallClicks(int TJMOfferwallClicks) {
        this.TJMOfferwallClicks = TJMOfferwallClicks;
    }

    public int[] getTJMOfferwallClicksHourly() {
        return TJMOfferwallClicksHourly;
    }

    public void setTJMOfferwallClicksHourly(int[] TJMOfferwallClicksHourly) {
        this.TJMOfferwallClicksHourly = TJMOfferwallClicksHourly;
    }

    public int getTJMOfferwallConversions() {
        return TJMOfferwallConversions;
    }

    public void setTJMOfferwallConversions(int TJMOfferwallConversions) {
        this.TJMOfferwallConversions = TJMOfferwallConversions;
    }

    public int[] getTJMOfferwallConversionsHourly() {
        return TJMOfferwallConversionsHourly;
    }

    public void setTJMOfferwallConversionsHourly(int[] TJMOfferwallConversionsHourly) {
        this.TJMOfferwallConversionsHourly = TJMOfferwallConversionsHourly;
    }

    public double getDirectPlayRevenue() {
        return DirectPlayRevenue;
    }

    public void setDirectPlayRevenue(double directPlayRevenue) {
        DirectPlayRevenue = directPlayRevenue;
    }

    public double[] getDirectPlayRevenueHourly() {
        return DirectPlayRevenueHourly;
    }

    public void setDirectPlayRevenueHourly(double[] directPlayRevenueHourly) {
        DirectPlayRevenueHourly = directPlayRevenueHourly;
    }

    public int getDirectPlayImpressions() {
        return DirectPlayImpressions;
    }

    public void setDirectPlayImpressions(int directPlayImpressions) {
        DirectPlayImpressions = directPlayImpressions;
    }

    public int[] getDirectPlayImpressionsHourly() {
        return DirectPlayImpressionsHourly;
    }

    public void setDirectPlayImpressionsHourly(int[] directPlayImpressionsHourly) {
        DirectPlayImpressionsHourly = directPlayImpressionsHourly;
    }

    public int getDirectPlayClicks() {
        return DirectPlayClicks;
    }

    public void setDirectPlayClicks(int directPlayClicks) {
        DirectPlayClicks = directPlayClicks;
    }

    public int[] getDirectPlayClicksHourly() {
        return DirectPlayClicksHourly;
    }

    public void setDirectPlayClicksHourly(int[] directPlayClicksHourly) {
        DirectPlayClicksHourly = directPlayClicksHourly;
    }

    public int getDirectPlayConversions() {
        return DirectPlayConversions;
    }

    public void setDirectPlayConversions(int directPlayConversions) {
        DirectPlayConversions = directPlayConversions;
    }

    public int[] getDirectPlayConversionsHourly() {
        return DirectPlayConversionsHourly;
    }

    public void setDirectPlayConversionsHourly(int[] directPlayConversionsHourly) {
        DirectPlayConversionsHourly = directPlayConversionsHourly;
    }
}
