package sa.revenue.general.db;

import sa.revenue.general.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "TAPJOY_APP".
 */
public class TapjoyApp {

    private Long id;
    private long AdPlacementId;
    private String Date;
    private String Name;
    private String AppStoreID;
    private String AppName;
    private String AppKey;
    private String url;
    private String Platform;
    private Boolean Rewarded;
    private String OfferType;
    private Integer PaidInstalls;
    private String PaidInstallsHourly;
    private Integer PaidClicks;
    private String PaidClicksHourly;
    private Double Spend;
    private String SpendHourly;
    private Integer Sessions;
    private String SessionsHourly;
    private Integer NewUsers;
    private String NewUsersHourly;
    private Integer DailyActiveUsers;
    private Double OfferwallRevenue;
    private String OfferwallRevenueHourly;
    private Integer OfferwallImpressions;
    private String OfferwallImpressionsHourly;
    private Integer OfferwallClicks;
    private String OfferwallClicksHourly;
    private Integer OfferwallConversions;
    private String OfferwallConversionsHourly;
    private Double FeaturedOfferRevenue;
    private String FeaturedOfferRevenueHourly;
    private Integer FeaturedOfferImpressions;
    private String FeaturedOfferImpressionsHourly;
    private Integer FeaturedOfferClicks;
    private String FeaturedOfferClicksHourly;
    private Integer FeaturedOfferConversions;
    private String FeaturedOfferConversionsHourly;
    private Double TJMOfferwallRevenue;
    private String TJMOfferwallRevenueHourly;
    private Integer TJMOfferwallImpressions;
    private String TJMOfferwallImpressionsHourly;
    private Integer TJMOfferwallClicks;
    private String TJMOfferwallClicksHourly;
    private Integer TJMOfferwallConversions;
    private String TJMOfferwallConversionsHourly;
    private Double DirectPlayRevenue;
    private String DirectPlayRevenueHourly;
    private Integer DirectPlayImpressions;
    private String DirectPlayImpressionsHourly;
    private Integer DirectPlayClicks;
    private String DirectPlayClicksHourly;
    private Integer DirectPlayConversions;
    private String DirectPlayConversionsHourly;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TapjoyAppDao myDao;

    private AdPlacement adPlacement;
    private Long adPlacement__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public TapjoyApp() {
    }

    public TapjoyApp(Long id) {
        this.id = id;
    }

    public TapjoyApp(Long id, long AdPlacementId, String Date, String Name, String AppStoreID, String AppName, String AppKey, String url, String Platform, Boolean Rewarded, String OfferType, Integer PaidInstalls, String PaidInstallsHourly, Integer PaidClicks, String PaidClicksHourly, Double Spend, String SpendHourly, Integer Sessions, String SessionsHourly, Integer NewUsers, String NewUsersHourly, Integer DailyActiveUsers, Double OfferwallRevenue, String OfferwallRevenueHourly, Integer OfferwallImpressions, String OfferwallImpressionsHourly, Integer OfferwallClicks, String OfferwallClicksHourly, Integer OfferwallConversions, String OfferwallConversionsHourly, Double FeaturedOfferRevenue, String FeaturedOfferRevenueHourly, Integer FeaturedOfferImpressions, String FeaturedOfferImpressionsHourly, Integer FeaturedOfferClicks, String FeaturedOfferClicksHourly, Integer FeaturedOfferConversions, String FeaturedOfferConversionsHourly, Double TJMOfferwallRevenue, String TJMOfferwallRevenueHourly, Integer TJMOfferwallImpressions, String TJMOfferwallImpressionsHourly, Integer TJMOfferwallClicks, String TJMOfferwallClicksHourly, Integer TJMOfferwallConversions, String TJMOfferwallConversionsHourly, Double DirectPlayRevenue, String DirectPlayRevenueHourly, Integer DirectPlayImpressions, String DirectPlayImpressionsHourly, Integer DirectPlayClicks, String DirectPlayClicksHourly, Integer DirectPlayConversions, String DirectPlayConversionsHourly) {
        this.id = id;
        this.AdPlacementId = AdPlacementId;
        this.Date = Date;
        this.Name = Name;
        this.AppStoreID = AppStoreID;
        this.AppName = AppName;
        this.AppKey = AppKey;
        this.url = url;
        this.Platform = Platform;
        this.Rewarded = Rewarded;
        this.OfferType = OfferType;
        this.PaidInstalls = PaidInstalls;
        this.PaidInstallsHourly = PaidInstallsHourly;
        this.PaidClicks = PaidClicks;
        this.PaidClicksHourly = PaidClicksHourly;
        this.Spend = Spend;
        this.SpendHourly = SpendHourly;
        this.Sessions = Sessions;
        this.SessionsHourly = SessionsHourly;
        this.NewUsers = NewUsers;
        this.NewUsersHourly = NewUsersHourly;
        this.DailyActiveUsers = DailyActiveUsers;
        this.OfferwallRevenue = OfferwallRevenue;
        this.OfferwallRevenueHourly = OfferwallRevenueHourly;
        this.OfferwallImpressions = OfferwallImpressions;
        this.OfferwallImpressionsHourly = OfferwallImpressionsHourly;
        this.OfferwallClicks = OfferwallClicks;
        this.OfferwallClicksHourly = OfferwallClicksHourly;
        this.OfferwallConversions = OfferwallConversions;
        this.OfferwallConversionsHourly = OfferwallConversionsHourly;
        this.FeaturedOfferRevenue = FeaturedOfferRevenue;
        this.FeaturedOfferRevenueHourly = FeaturedOfferRevenueHourly;
        this.FeaturedOfferImpressions = FeaturedOfferImpressions;
        this.FeaturedOfferImpressionsHourly = FeaturedOfferImpressionsHourly;
        this.FeaturedOfferClicks = FeaturedOfferClicks;
        this.FeaturedOfferClicksHourly = FeaturedOfferClicksHourly;
        this.FeaturedOfferConversions = FeaturedOfferConversions;
        this.FeaturedOfferConversionsHourly = FeaturedOfferConversionsHourly;
        this.TJMOfferwallRevenue = TJMOfferwallRevenue;
        this.TJMOfferwallRevenueHourly = TJMOfferwallRevenueHourly;
        this.TJMOfferwallImpressions = TJMOfferwallImpressions;
        this.TJMOfferwallImpressionsHourly = TJMOfferwallImpressionsHourly;
        this.TJMOfferwallClicks = TJMOfferwallClicks;
        this.TJMOfferwallClicksHourly = TJMOfferwallClicksHourly;
        this.TJMOfferwallConversions = TJMOfferwallConversions;
        this.TJMOfferwallConversionsHourly = TJMOfferwallConversionsHourly;
        this.DirectPlayRevenue = DirectPlayRevenue;
        this.DirectPlayRevenueHourly = DirectPlayRevenueHourly;
        this.DirectPlayImpressions = DirectPlayImpressions;
        this.DirectPlayImpressionsHourly = DirectPlayImpressionsHourly;
        this.DirectPlayClicks = DirectPlayClicks;
        this.DirectPlayClicksHourly = DirectPlayClicksHourly;
        this.DirectPlayConversions = DirectPlayConversions;
        this.DirectPlayConversionsHourly = DirectPlayConversionsHourly;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTapjoyAppDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAdPlacementId() {
        return AdPlacementId;
    }

    public void setAdPlacementId(long AdPlacementId) {
        this.AdPlacementId = AdPlacementId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAppStoreID() {
        return AppStoreID;
    }

    public void setAppStoreID(String AppStoreID) {
        this.AppStoreID = AppStoreID;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String AppKey) {
        this.AppKey = AppKey;
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

    public void setPlatform(String Platform) {
        this.Platform = Platform;
    }

    public Boolean getRewarded() {
        return Rewarded;
    }

    public void setRewarded(Boolean Rewarded) {
        this.Rewarded = Rewarded;
    }

    public String getOfferType() {
        return OfferType;
    }

    public void setOfferType(String OfferType) {
        this.OfferType = OfferType;
    }

    public Integer getPaidInstalls() {
        return PaidInstalls;
    }

    public void setPaidInstalls(Integer PaidInstalls) {
        this.PaidInstalls = PaidInstalls;
    }

    public String getPaidInstallsHourly() {
        return PaidInstallsHourly;
    }

    public void setPaidInstallsHourly(String PaidInstallsHourly) {
        this.PaidInstallsHourly = PaidInstallsHourly;
    }

    public Integer getPaidClicks() {
        return PaidClicks;
    }

    public void setPaidClicks(Integer PaidClicks) {
        this.PaidClicks = PaidClicks;
    }

    public String getPaidClicksHourly() {
        return PaidClicksHourly;
    }

    public void setPaidClicksHourly(String PaidClicksHourly) {
        this.PaidClicksHourly = PaidClicksHourly;
    }

    public Double getSpend() {
        return Spend;
    }

    public void setSpend(Double Spend) {
        this.Spend = Spend;
    }

    public String getSpendHourly() {
        return SpendHourly;
    }

    public void setSpendHourly(String SpendHourly) {
        this.SpendHourly = SpendHourly;
    }

    public Integer getSessions() {
        return Sessions;
    }

    public void setSessions(Integer Sessions) {
        this.Sessions = Sessions;
    }

    public String getSessionsHourly() {
        return SessionsHourly;
    }

    public void setSessionsHourly(String SessionsHourly) {
        this.SessionsHourly = SessionsHourly;
    }

    public Integer getNewUsers() {
        return NewUsers;
    }

    public void setNewUsers(Integer NewUsers) {
        this.NewUsers = NewUsers;
    }

    public String getNewUsersHourly() {
        return NewUsersHourly;
    }

    public void setNewUsersHourly(String NewUsersHourly) {
        this.NewUsersHourly = NewUsersHourly;
    }

    public Integer getDailyActiveUsers() {
        return DailyActiveUsers;
    }

    public void setDailyActiveUsers(Integer DailyActiveUsers) {
        this.DailyActiveUsers = DailyActiveUsers;
    }

    public Double getOfferwallRevenue() {
        return OfferwallRevenue;
    }

    public void setOfferwallRevenue(Double OfferwallRevenue) {
        this.OfferwallRevenue = OfferwallRevenue;
    }

    public String getOfferwallRevenueHourly() {
        return OfferwallRevenueHourly;
    }

    public void setOfferwallRevenueHourly(String OfferwallRevenueHourly) {
        this.OfferwallRevenueHourly = OfferwallRevenueHourly;
    }

    public Integer getOfferwallImpressions() {
        return OfferwallImpressions;
    }

    public void setOfferwallImpressions(Integer OfferwallImpressions) {
        this.OfferwallImpressions = OfferwallImpressions;
    }

    public String getOfferwallImpressionsHourly() {
        return OfferwallImpressionsHourly;
    }

    public void setOfferwallImpressionsHourly(String OfferwallImpressionsHourly) {
        this.OfferwallImpressionsHourly = OfferwallImpressionsHourly;
    }

    public Integer getOfferwallClicks() {
        return OfferwallClicks;
    }

    public void setOfferwallClicks(Integer OfferwallClicks) {
        this.OfferwallClicks = OfferwallClicks;
    }

    public String getOfferwallClicksHourly() {
        return OfferwallClicksHourly;
    }

    public void setOfferwallClicksHourly(String OfferwallClicksHourly) {
        this.OfferwallClicksHourly = OfferwallClicksHourly;
    }

    public Integer getOfferwallConversions() {
        return OfferwallConversions;
    }

    public void setOfferwallConversions(Integer OfferwallConversions) {
        this.OfferwallConversions = OfferwallConversions;
    }

    public String getOfferwallConversionsHourly() {
        return OfferwallConversionsHourly;
    }

    public void setOfferwallConversionsHourly(String OfferwallConversionsHourly) {
        this.OfferwallConversionsHourly = OfferwallConversionsHourly;
    }

    public Double getFeaturedOfferRevenue() {
        return FeaturedOfferRevenue;
    }

    public void setFeaturedOfferRevenue(Double FeaturedOfferRevenue) {
        this.FeaturedOfferRevenue = FeaturedOfferRevenue;
    }

    public String getFeaturedOfferRevenueHourly() {
        return FeaturedOfferRevenueHourly;
    }

    public void setFeaturedOfferRevenueHourly(String FeaturedOfferRevenueHourly) {
        this.FeaturedOfferRevenueHourly = FeaturedOfferRevenueHourly;
    }

    public Integer getFeaturedOfferImpressions() {
        return FeaturedOfferImpressions;
    }

    public void setFeaturedOfferImpressions(Integer FeaturedOfferImpressions) {
        this.FeaturedOfferImpressions = FeaturedOfferImpressions;
    }

    public String getFeaturedOfferImpressionsHourly() {
        return FeaturedOfferImpressionsHourly;
    }

    public void setFeaturedOfferImpressionsHourly(String FeaturedOfferImpressionsHourly) {
        this.FeaturedOfferImpressionsHourly = FeaturedOfferImpressionsHourly;
    }

    public Integer getFeaturedOfferClicks() {
        return FeaturedOfferClicks;
    }

    public void setFeaturedOfferClicks(Integer FeaturedOfferClicks) {
        this.FeaturedOfferClicks = FeaturedOfferClicks;
    }

    public String getFeaturedOfferClicksHourly() {
        return FeaturedOfferClicksHourly;
    }

    public void setFeaturedOfferClicksHourly(String FeaturedOfferClicksHourly) {
        this.FeaturedOfferClicksHourly = FeaturedOfferClicksHourly;
    }

    public Integer getFeaturedOfferConversions() {
        return FeaturedOfferConversions;
    }

    public void setFeaturedOfferConversions(Integer FeaturedOfferConversions) {
        this.FeaturedOfferConversions = FeaturedOfferConversions;
    }

    public String getFeaturedOfferConversionsHourly() {
        return FeaturedOfferConversionsHourly;
    }

    public void setFeaturedOfferConversionsHourly(String FeaturedOfferConversionsHourly) {
        this.FeaturedOfferConversionsHourly = FeaturedOfferConversionsHourly;
    }

    public Double getTJMOfferwallRevenue() {
        return TJMOfferwallRevenue;
    }

    public void setTJMOfferwallRevenue(Double TJMOfferwallRevenue) {
        this.TJMOfferwallRevenue = TJMOfferwallRevenue;
    }

    public String getTJMOfferwallRevenueHourly() {
        return TJMOfferwallRevenueHourly;
    }

    public void setTJMOfferwallRevenueHourly(String TJMOfferwallRevenueHourly) {
        this.TJMOfferwallRevenueHourly = TJMOfferwallRevenueHourly;
    }

    public Integer getTJMOfferwallImpressions() {
        return TJMOfferwallImpressions;
    }

    public void setTJMOfferwallImpressions(Integer TJMOfferwallImpressions) {
        this.TJMOfferwallImpressions = TJMOfferwallImpressions;
    }

    public String getTJMOfferwallImpressionsHourly() {
        return TJMOfferwallImpressionsHourly;
    }

    public void setTJMOfferwallImpressionsHourly(String TJMOfferwallImpressionsHourly) {
        this.TJMOfferwallImpressionsHourly = TJMOfferwallImpressionsHourly;
    }

    public Integer getTJMOfferwallClicks() {
        return TJMOfferwallClicks;
    }

    public void setTJMOfferwallClicks(Integer TJMOfferwallClicks) {
        this.TJMOfferwallClicks = TJMOfferwallClicks;
    }

    public String getTJMOfferwallClicksHourly() {
        return TJMOfferwallClicksHourly;
    }

    public void setTJMOfferwallClicksHourly(String TJMOfferwallClicksHourly) {
        this.TJMOfferwallClicksHourly = TJMOfferwallClicksHourly;
    }

    public Integer getTJMOfferwallConversions() {
        return TJMOfferwallConversions;
    }

    public void setTJMOfferwallConversions(Integer TJMOfferwallConversions) {
        this.TJMOfferwallConversions = TJMOfferwallConversions;
    }

    public String getTJMOfferwallConversionsHourly() {
        return TJMOfferwallConversionsHourly;
    }

    public void setTJMOfferwallConversionsHourly(String TJMOfferwallConversionsHourly) {
        this.TJMOfferwallConversionsHourly = TJMOfferwallConversionsHourly;
    }

    public Double getDirectPlayRevenue() {
        return DirectPlayRevenue;
    }

    public void setDirectPlayRevenue(Double DirectPlayRevenue) {
        this.DirectPlayRevenue = DirectPlayRevenue;
    }

    public String getDirectPlayRevenueHourly() {
        return DirectPlayRevenueHourly;
    }

    public void setDirectPlayRevenueHourly(String DirectPlayRevenueHourly) {
        this.DirectPlayRevenueHourly = DirectPlayRevenueHourly;
    }

    public Integer getDirectPlayImpressions() {
        return DirectPlayImpressions;
    }

    public void setDirectPlayImpressions(Integer DirectPlayImpressions) {
        this.DirectPlayImpressions = DirectPlayImpressions;
    }

    public String getDirectPlayImpressionsHourly() {
        return DirectPlayImpressionsHourly;
    }

    public void setDirectPlayImpressionsHourly(String DirectPlayImpressionsHourly) {
        this.DirectPlayImpressionsHourly = DirectPlayImpressionsHourly;
    }

    public Integer getDirectPlayClicks() {
        return DirectPlayClicks;
    }

    public void setDirectPlayClicks(Integer DirectPlayClicks) {
        this.DirectPlayClicks = DirectPlayClicks;
    }

    public String getDirectPlayClicksHourly() {
        return DirectPlayClicksHourly;
    }

    public void setDirectPlayClicksHourly(String DirectPlayClicksHourly) {
        this.DirectPlayClicksHourly = DirectPlayClicksHourly;
    }

    public Integer getDirectPlayConversions() {
        return DirectPlayConversions;
    }

    public void setDirectPlayConversions(Integer DirectPlayConversions) {
        this.DirectPlayConversions = DirectPlayConversions;
    }

    public String getDirectPlayConversionsHourly() {
        return DirectPlayConversionsHourly;
    }

    public void setDirectPlayConversionsHourly(String DirectPlayConversionsHourly) {
        this.DirectPlayConversionsHourly = DirectPlayConversionsHourly;
    }

    /** To-one relationship, resolved on first access. */
    public AdPlacement getAdPlacement() {
        long __key = this.AdPlacementId;
        if (adPlacement__resolvedKey == null || !adPlacement__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AdPlacementDao targetDao = daoSession.getAdPlacementDao();
            AdPlacement adPlacementNew = targetDao.load(__key);
            synchronized (this) {
                adPlacement = adPlacementNew;
            	adPlacement__resolvedKey = __key;
            }
        }
        return adPlacement;
    }

    public void setAdPlacement(AdPlacement adPlacement) {
        if (adPlacement == null) {
            throw new DaoException("To-one property 'AdPlacementId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.adPlacement = adPlacement;
            AdPlacementId = adPlacement.getId();
            adPlacement__resolvedKey = AdPlacementId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
