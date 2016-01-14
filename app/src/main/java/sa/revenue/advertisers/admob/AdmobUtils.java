package sa.revenue.advertisers.admob;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.List;

import sa.revenue.AppController;
import sa.revenue.R;
import sa.revenue.general.db.AdPlacement;
import sa.revenue.general.db.AdPlacementDao;
import sa.revenue.general.db.AdmobAdUnit;
import sa.revenue.general.db.AdmobAdUnitDao;
import sa.revenue.general.db.AdmobDay;
import sa.revenue.general.db.AdmobDayDao;
import sa.revenue.general.db.DaoSession;


//TODO - documentation
public class AdmobUtils {
    public static String admob = "admob";
    public static SimpleDateFormat admobDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DaoSession daoSession = AppController.getInstance().getDaoSession();
    static AdPlacementDao adPlacementDao = daoSession.getAdPlacementDao();
    static AdmobAdUnitDao admobAdUnitDao = daoSession.getAdmobAdUnitDao();
    static AdmobDayDao admobDayDao = daoSession.getAdmobDayDao();

    public static boolean hasData() {
        return admobDayDao.count() > 0;
    }

    public static String getLastParsedDate(Context context) {
        String lastParsedDate = Hawk.get(context.getString(R.string.admob_start_date_key));
        List<AdmobDay> admobDays = admobDayDao.queryBuilder().orderDesc(AdmobDayDao.Properties.Date).limit(1).list();
        if (admobDays.size() > 0)
            lastParsedDate = admobDays.get(0).getDate();
        return lastParsedDate;
    }

    public static void storeResultRowAsAdmobAdUnit(List<String> adUnit) {
        if (adUnit.size() == 19) {
            AdmobAdUnit admobAdUnit = new AdmobAdUnit();
            admobAdUnit.setId(null);
            //Save app
            long adPlacementId;
            AdPlacement adPlacement = adPlacementDao.queryBuilder().where(AdPlacementDao.Properties.Advertiser.eq(admob), AdPlacementDao.Properties.AdvertiserAdId.eq(adUnit.get(2))).unique();
            if (adPlacement != null) {
                adPlacementId = adPlacement.getId();
            } else {
                adPlacement = new AdPlacement();
                adPlacement.setId(null);
                adPlacement.setAdvertiser(admob);
                adPlacement.setAdvertiserAdId(adUnit.get(2));
                adPlacement.setAdvertiserAdName(adUnit.get(3));
                adPlacementId = adPlacementDao.insert(adPlacement);
            }
            admobAdUnit.setAdPlacementId(adPlacementId);
            admobAdUnit.setAdClientId(adUnit.get(0));
            admobAdUnit.setAdUnitCode(adUnit.get(1));
            admobAdUnit.setAdUnitId(adUnit.get(2));
            admobAdUnit.setAdUnitName(adUnit.get(3));
            admobAdUnit.setDate(adUnit.get(4));
            admobAdUnit.setAdRequests(adUnit.get(5));
            admobAdUnit.setAdRequestsCoverage(adUnit.get(6));
            admobAdUnit.setClicks(adUnit.get(7));
            admobAdUnit.setCostPerClick(adUnit.get(8));
            admobAdUnit.setEarnings(adUnit.get(9));
            admobAdUnit.setIndividualAdImpressions(adUnit.get(10));
            admobAdUnit.setIndividualAdImpressionsCtr(adUnit.get(11));
            admobAdUnit.setIndividualAdImpressionsRpm(adUnit.get(12));
            admobAdUnit.setMatchedAdRequests(adUnit.get(13));
            admobAdUnit.setMatchedAdRequestsCtr(adUnit.get(14));
            admobAdUnit.setMatchedAdRequestsRpm(adUnit.get(15));
            admobAdUnit.setPageViews(adUnit.get(16));
            admobAdUnit.setPageViewsCtr(adUnit.get(17));
            admobAdUnit.setPageViewsRpm(adUnit.get(18));
            admobAdUnitDao.insert(admobAdUnit);
        }
    }

    public static void storeResultRowAdAdmobDay(String date, List<String> day) {
        if (day.size() == 19) {
            AdmobDay admobDay = new AdmobDay();
            admobDay.setId(null);
            //No Dimensions
            admobDay.setDate(date);
            admobDay.setAdRequests(day.get(5));
            admobDay.setAdRequestsCoverage(day.get(6));
            admobDay.setClicks(day.get(7));
            admobDay.setCostPerClick(day.get(8));
            admobDay.setEarnings(day.get(9));
            admobDay.setIndividualAdImpressions(day.get(10));
            admobDay.setIndividualAdImpressionsCtr(day.get(11));
            admobDay.setIndividualAdImpressionsRpm(day.get(12));
            admobDay.setMatchedAdRequests(day.get(13));
            admobDay.setMatchedAdRequestsCtr(day.get(14));
            admobDay.setMatchedAdRequestsRpm(day.get(15));
            admobDay.setPageViews(day.get(16));
            admobDay.setPageViewsCtr(day.get(17));
            admobDay.setPageViewsRpm(day.get(18));
            admobDayDao.insert(admobDay);

        }
    }

    public static long getDaysInDB() {
        return admobDayDao.queryBuilder().count();
    }

    public static long getAppsInDB() {
        return admobAdUnitDao.queryBuilder().count();
    }

    public static void clearDayAndAppTable() {
        admobAdUnitDao.deleteAll();
        admobDayDao.deleteAll();
    }

    public static boolean hasParsedDate(String date) {
        return admobDayDao.queryBuilder().where(AdmobDayDao.Properties.Date.eq(date)).count() > 0;
    }

    public static boolean admobSetupCorrect(Context context) {
        String startDate = Hawk.get(context.getString(R.string.admob_start_date_key));
        String admobEmail = Hawk.get(context.getString(R.string.admob_email_key));
        String admobAccountId = Hawk.get(context.getString(R.string.admob_account_id_key));
        return (startDate != null && admobEmail != null && admobAccountId != null);
    }

    public static void deleteDataFromDay(String date) {
        //Try to remove apps and day which are on startDate, this to prevent duplicate entries.
        List<AdmobAdUnit> admobAdUnits = admobAdUnitDao.queryBuilder().where(AdmobAdUnitDao.Properties.Date.eq(date)).list();
        for (AdmobAdUnit adUnit : admobAdUnits) {
            admobAdUnitDao.delete(adUnit);
        }
        List<AdmobDay> admobDays = admobDayDao.queryBuilder().where(AdmobDayDao.Properties.Date.eq(date)).list();
        for (AdmobDay admobDay : admobDays) {
            admobDayDao.delete(admobDay);
        }
    }



    public static void cleanUpDatabaseBefore(String date) {
        List<AdmobDay> admobDays = admobDayDao.queryBuilder().where(AdmobDayDao.Properties.Date.lt(date)).list();
        for (AdmobDay admobDay : admobDays) {
            admobDayDao.delete(admobDay);
        }
        List<AdmobAdUnit> admobAdUnits = admobAdUnitDao.queryBuilder().where(AdmobAdUnitDao.Properties.Date.lt(date)).list();
        for (AdmobAdUnit adUnit : admobAdUnits) {
            admobAdUnitDao.delete(adUnit);
        }
    }

}
