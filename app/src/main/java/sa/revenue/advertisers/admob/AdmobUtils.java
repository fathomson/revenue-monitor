package sa.revenue.advertisers.admob;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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


/**
 * Created by un on 1/5/2016.
 */
public class AdmobUtils {
    public static String admob = "admob";
    static SimpleDateFormat admobDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DaoSession daoSession = AppController.getInstance().getDaoSession();
    static AdPlacementDao adPlacementDao = daoSession.getAdPlacementDao();
    static AdmobAdUnitDao admobAdUnitDao = daoSession.getAdmobAdUnitDao();
    static AdmobDayDao admobDayDao = daoSession.getAdmobDayDao();

    public static boolean hasData() {
        return admobDayDao.count() > 0;
    }

    public static String getLastParsedDate(Context context) {
        String lastParsedDate = Hawk.get(context.getString(R.string.admob_start_date_key));
        List<AdmobDay> admobDays = admobDayDao.queryBuilder().orderDesc(AdmobDayDao.Properties.Id).limit(1).list();
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

    public static boolean shouldParseNextDay(String oldDate) throws ParseException {
        Calendar old = Calendar.getInstance();
        old.setTime(admobDateFormat.parse(oldDate));
        old.set(Calendar.HOUR_OF_DAY, 23);
        old.set(Calendar.MINUTE, 59);
        old.set(Calendar.SECOND, 59);
        return old.before(Calendar.getInstance());
    }

    public static String nextDayString(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(admobDateFormat.parse(date));
        c.add(Calendar.DATE, 1);
        return admobDateFormat.format(new Date(c.getTimeInMillis()));
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

    public static String todayString() throws ParseException {
        Calendar c = Calendar.getInstance();
        return admobDateFormat.format(new Date(c.getTimeInMillis()));
    }

    public static void deleteDataFromDay(String date){
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

    public static Calendar getCalendar(String date){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(admobDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

}
