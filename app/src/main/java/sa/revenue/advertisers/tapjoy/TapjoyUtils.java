package sa.revenue.advertisers.tapjoy;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sa.revenue.AppController;
import sa.revenue.R;
import sa.revenue.advertisers.tapjoy.model.api.App;
import sa.revenue.advertisers.tapjoy.model.api.Day;
import sa.revenue.general.db.AdPlacement;
import sa.revenue.general.db.AdPlacementDao;
import sa.revenue.general.db.DaoSession;
import sa.revenue.general.db.TapjoyApp;
import sa.revenue.general.db.TapjoyAppDao;
import sa.revenue.general.db.TapjoyDay;
import sa.revenue.general.db.TapjoyDayDao;


public class TapjoyUtils {
    public static String tapjoy = "tapjoy";
    static SimpleDateFormat tapjoyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static DaoSession daoSession = AppController.getInstance().getDaoSession();
    static AdPlacementDao adPlacementDao = daoSession.getAdPlacementDao();
    static TapjoyAppDao tapjoyAppDao = daoSession.getTapjoyAppDao();
    static TapjoyDayDao tapjoyDayDao = daoSession.getTapjoyDayDao();

    public static boolean hasData() {
        return tapjoyDayDao.count() > 0;
    }

    public static void storeApiToDb(Day day) {
        String date = day.getDate();
        TapjoyDay tapjoyDay = new TapjoyDay();
        tapjoyDay.setId(null);
        tapjoyDay.setDate(date);
        tapjoyDay.setAppCount(day.getApps().size());
        for (App apiApp : day.getApps()) {
            //Save app
            long adPlacementId;
            AdPlacement adPlacement = adPlacementDao.queryBuilder().where(AdPlacementDao.Properties.Advertiser.eq(tapjoy), AdPlacementDao.Properties.AdvertiserAdId.eq(apiApp.getAppKey())).unique();
            if (adPlacement != null) {
                adPlacementId = adPlacement.getId();
            } else {
                adPlacement = new AdPlacement();
                adPlacement.setId(null);
                adPlacement.setAdvertiser(tapjoy);
                adPlacement.setAdvertiserAdId(apiApp.getAppKey());
                adPlacement.setAdvertiserAdName(apiApp.getName());
                adPlacementId = adPlacementDao.insert(adPlacement);
            }

            tapjoyAppDao.insert(ApiAppToDbApp(adPlacementId, date, apiApp));
            //sum app values
            tapjoyDay.setPaidInstallsSum(calculateIntegerSum(tapjoyDay.getPaidInstallsSum(), apiApp.getPaidInstalls()));
            tapjoyDay.setPaidInstallsHourly(sumIntArray(tapjoyDay.getPaidInstallsHourly(), apiApp.getPaidInstallsHourly()));
            tapjoyDay.setPaidClicksSum(calculateIntegerSum(tapjoyDay.getPaidClicksSum(), apiApp.getPaidClicks()));
            tapjoyDay.setPaidClicksHourly(sumIntArray(tapjoyDay.getPaidClicksHourly(), apiApp.getPaidClicksHourly()));
            tapjoyDay.setSpendSum(calculateDoubleSum(tapjoyDay.getSpendSum(), apiApp.getSpend()));
            tapjoyDay.setSpendHourly(sumDoubleArray(tapjoyDay.getSpendHourly(), apiApp.getSpendHourly()));
            tapjoyDay.setSessionsSum(calculateIntegerSum(tapjoyDay.getSessionsSum(), apiApp.getSessions()));
            tapjoyDay.setSessionsHourly(sumIntArray(tapjoyDay.getSessionsHourly(), apiApp.getSessionsHourly()));
            tapjoyDay.setNewUsersSum(calculateIntegerSum(tapjoyDay.getNewUsersSum(), apiApp.getNewUsers()));
            tapjoyDay.setNewUsersHourly(sumIntArray(tapjoyDay.getNewUsersHourly(), apiApp.getNewUsersHourly()));
            tapjoyDay.setDailyActiveUsersSum(calculateIntegerSum(tapjoyDay.getDailyActiveUsersSum(), apiApp.getDailyActiveUsers()));
            tapjoyDay.setOfferwallRevenueSum(calculateDoubleSum(tapjoyDay.getOfferwallRevenueSum(), apiApp.getOfferwallRevenue()));
            tapjoyDay.setOfferwallRevenueHourly(sumDoubleArray(tapjoyDay.getOfferwallRevenueHourly(), apiApp.getOfferwallRevenueHourly()));
            tapjoyDay.setOfferwallImpressionsSum(calculateIntegerSum(tapjoyDay.getOfferwallImpressionsSum(), apiApp.getOfferwallImpressions()));
            tapjoyDay.setOfferwallImpressionsHourly(sumIntArray(tapjoyDay.getOfferwallImpressionsHourly(), apiApp.getOfferwallImpressionsHourly()));
            tapjoyDay.setOfferwallClicksSum(calculateIntegerSum(tapjoyDay.getOfferwallClicksSum(), apiApp.getOfferwallClicks()));
            tapjoyDay.setOfferwallClicksHourly(sumIntArray(tapjoyDay.getOfferwallClicksHourly(), apiApp.getOfferwallClicksHourly()));
            tapjoyDay.setOfferwallConversionsSum(calculateIntegerSum(tapjoyDay.getOfferwallConversionsSum(), apiApp.getOfferwallConversions()));
            tapjoyDay.setOfferwallConversionsHourly(sumIntArray(tapjoyDay.getOfferwallConversionsHourly(), apiApp.getOfferwallConversionsHourly()));
            tapjoyDay.setFeaturedOfferRevenueSum(calculateDoubleSum(tapjoyDay.getFeaturedOfferRevenueSum(), apiApp.getFeaturedOfferRevenue()));
            tapjoyDay.setFeaturedOfferRevenueHourly(sumDoubleArray(tapjoyDay.getFeaturedOfferRevenueHourly(), apiApp.getFeaturedOfferRevenueHourly()));
            tapjoyDay.setFeaturedOfferImpressionsSum(calculateIntegerSum(tapjoyDay.getFeaturedOfferImpressionsSum(), apiApp.getFeaturedOfferImpressions()));
            tapjoyDay.setFeaturedOfferImpressionsHourly(sumIntArray(tapjoyDay.getFeaturedOfferImpressionsHourly(), apiApp.getFeaturedOfferImpressionsHourly()));
            tapjoyDay.setFeaturedOfferClicksSum(calculateIntegerSum(tapjoyDay.getFeaturedOfferClicksSum(), apiApp.getFeaturedOfferClicks()));
            tapjoyDay.setFeaturedOfferClicksHourly(sumIntArray(tapjoyDay.getFeaturedOfferClicksHourly(), apiApp.getFeaturedOfferClicksHourly()));
            tapjoyDay.setFeaturedOfferConversionsSum(calculateIntegerSum(tapjoyDay.getFeaturedOfferConversionsSum(), apiApp.getFeaturedOfferConversions()));
            tapjoyDay.setFeaturedOfferConversionsHourly(sumIntArray(tapjoyDay.getFeaturedOfferConversionsHourly(), apiApp.getFeaturedOfferConversionsHourly()));
            tapjoyDay.setTJMOfferwallRevenueSum(calculateDoubleSum(tapjoyDay.getTJMOfferwallRevenueSum(), apiApp.getTJMOfferwallRevenue()));
            tapjoyDay.setTJMOfferwallRevenueHourly(sumDoubleArray(tapjoyDay.getTJMOfferwallRevenueHourly(), apiApp.getTJMOfferwallRevenueHourly()));
            tapjoyDay.setTJMOfferwallImpressionsSum(calculateIntegerSum(tapjoyDay.getTJMOfferwallImpressionsSum(), apiApp.getTJMOfferwallImpressions()));
            tapjoyDay.setTJMOfferwallImpressionsHourly(sumIntArray(tapjoyDay.getTJMOfferwallImpressionsHourly(), apiApp.getTJMOfferwallImpressionsHourly()));
            tapjoyDay.setTJMOfferwallClicksSum(calculateIntegerSum(tapjoyDay.getTJMOfferwallClicksSum(), apiApp.getTJMOfferwallClicks()));
            tapjoyDay.setTJMOfferwallClicksHourly(sumIntArray(tapjoyDay.getTJMOfferwallClicksHourly(), apiApp.getTJMOfferwallClicksHourly()));
            tapjoyDay.setTJMOfferwallConversionsSum(calculateIntegerSum(tapjoyDay.getTJMOfferwallConversionsSum(), apiApp.getTJMOfferwallConversions()));
            tapjoyDay.setTJMOfferwallConversionsHourly(sumIntArray(tapjoyDay.getTJMOfferwallConversionsHourly(), apiApp.getTJMOfferwallConversionsHourly()));
            tapjoyDay.setDirectPlayRevenueSum(calculateDoubleSum(tapjoyDay.getDirectPlayRevenueSum(), apiApp.getDirectPlayRevenue()));
            tapjoyDay.setDirectPlayRevenueHourly(sumDoubleArray(tapjoyDay.getDirectPlayRevenueHourly(), apiApp.getDirectPlayRevenueHourly()));
            tapjoyDay.setDirectPlayImpressionsSum(calculateIntegerSum(tapjoyDay.getDirectPlayImpressionsSum(), apiApp.getDirectPlayImpressions()));
            tapjoyDay.setDirectPlayImpressionsHourly(sumIntArray(tapjoyDay.getDirectPlayImpressionsHourly(), apiApp.getDirectPlayImpressionsHourly()));
            tapjoyDay.setDirectPlayClicksSum(calculateIntegerSum(tapjoyDay.getDirectPlayClicksSum(), apiApp.getDirectPlayClicks()));
            tapjoyDay.setDirectPlayClicksHourly(sumIntArray(tapjoyDay.getDirectPlayClicksHourly(), apiApp.getDirectPlayClicksHourly()));
            tapjoyDay.setDirectPlayConversionsSum(calculateIntegerSum(tapjoyDay.getDirectPlayConversionsSum(), apiApp.getDirectPlayConversions()));
            tapjoyDay.setDirectPlayConversionsHourly(sumIntArray(tapjoyDay.getDirectPlayConversionsHourly(), apiApp.getDirectPlayConversionsHourly()));
        }
        tapjoyDay.setConvertArraysToString();
        tapjoyDayDao.insert(tapjoyDay);
    }

    private static Integer calculateIntegerSum(Integer dayValue, Integer appValue) {
        return dayValue != null ? dayValue + appValue : appValue;

    }

    private static Double calculateDoubleSum(Double dayValue, Double appValue) {
        return dayValue != null ? dayValue + appValue : appValue;

    }


    private static TapjoyApp ApiAppToDbApp(long adPlacementId, String date, App apiApp) {
        return new TapjoyApp(null, adPlacementId, date,
                apiApp.getName(),
                apiApp.getAppStoreID(),
                apiApp.getAppName(),
                apiApp.getAppKey(),
                apiApp.getUrl(),
                apiApp.getPlatform(),
                apiApp.isRewarded(),
                apiApp.getOfferType(),
                apiApp.getPaidInstalls(),
                convertIntArrayToString(apiApp.getPaidInstallsHourly()),
                apiApp.getPaidClicks(),
                convertIntArrayToString(apiApp.getPaidClicksHourly()),
                apiApp.getSpend(),
                convertDoubleArrayToString(apiApp.getSpendHourly()),
                apiApp.getSessions(),
                convertIntArrayToString(apiApp.getSessionsHourly()),
                apiApp.getNewUsers(),
                convertIntArrayToString(apiApp.getNewUsersHourly()),
                apiApp.getDailyActiveUsers(),
                apiApp.getOfferwallRevenue(),
                convertDoubleArrayToString(apiApp.getOfferwallRevenueHourly()),
                apiApp.getOfferwallImpressions(),
                convertIntArrayToString(apiApp.getOfferwallImpressionsHourly()),
                apiApp.getOfferwallClicks(),
                convertIntArrayToString(apiApp.getOfferwallClicksHourly()),
                apiApp.getOfferwallConversions(),
                convertIntArrayToString(apiApp.getOfferwallConversionsHourly()),
                apiApp.getFeaturedOfferRevenue(),
                convertDoubleArrayToString(apiApp.getFeaturedOfferRevenueHourly()),
                apiApp.getFeaturedOfferImpressions(),
                convertIntArrayToString(apiApp.getFeaturedOfferImpressionsHourly()),
                apiApp.getFeaturedOfferClicks(),
                convertIntArrayToString(apiApp.getFeaturedOfferClicksHourly()),
                apiApp.getFeaturedOfferConversions(),
                convertIntArrayToString(apiApp.getFeaturedOfferConversionsHourly()),
                apiApp.getTJMOfferwallRevenue(),
                convertDoubleArrayToString(apiApp.getTJMOfferwallRevenueHourly()),
                apiApp.getTJMOfferwallImpressions(),
                convertIntArrayToString(apiApp.getTJMOfferwallImpressionsHourly()),
                apiApp.getTJMOfferwallClicks(),
                convertIntArrayToString(apiApp.getTJMOfferwallClicksHourly()),
                apiApp.getTJMOfferwallConversions(),
                convertIntArrayToString(apiApp.getTJMOfferwallConversionsHourly()),
                apiApp.getDirectPlayRevenue(),
                convertDoubleArrayToString(apiApp.getDirectPlayRevenueHourly()),
                apiApp.getDirectPlayImpressions(),
                convertIntArrayToString(apiApp.getDirectPlayImpressionsHourly()),
                apiApp.getDirectPlayClicks(),
                convertIntArrayToString(apiApp.getDirectPlayClicksHourly()),
                apiApp.getDirectPlayConversions(),
                convertIntArrayToString(apiApp.getDirectPlayConversionsHourly()));
    }

    public static String convertIntArrayToString(int[] array) {
        String LIST_SEPARATOR = ",";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : array) {
            stringBuffer.append(i).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        return stringBuffer.toString();
    }


    public static String convertDoubleArrayToString(double[] array) {
        String LIST_SEPARATOR = ",";
        StringBuffer stringBuffer = new StringBuffer();
        for (double d : array) {
            stringBuffer.append(d).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        return stringBuffer.toString();
    }

    private static int[] sumIntArray(int[] first, int[] second) {
        if (first == null) {
            return second;
        } else if (first.length == second.length) {
            int[] third = new int[first.length];
            for (int i = 0; i < first.length; i++) {
                third[i] = first[i] + second[i];
            }
            return third;
            //When first is null take second
        } else {
            return null;
        }
    }

    private static double[] sumDoubleArray(double[] first, double[] second) {
        if (first == null) {
            return second;
        } else if (first.length == second.length) {
            double[] third = new double[first.length];
            for (int i = 0; i < first.length; i++) {
                third[i] = first[i] + second[i];
            }
            return third;
            //When first is null take second

        } else {
            return null;
        }
    }

    public static boolean shouldParseNextDay(String oldDate) throws ParseException {
        Calendar old = Calendar.getInstance();
        old.setTime(tapjoyDateFormat.parse(oldDate));
        old.set(Calendar.HOUR_OF_DAY, 23);
        old.set(Calendar.MINUTE, 59);
        old.set(Calendar.SECOND, 59);
        return old.before(Calendar.getInstance());
    }

    public static String todayString() throws ParseException {
        Calendar c = Calendar.getInstance();
        return tapjoyDateFormat.format(new Date(c.getTimeInMillis()));
    }

    public static String nextDayString(String date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(tapjoyDateFormat.parse(date));
        c.add(Calendar.DATE, 1);
        return tapjoyDateFormat.format(new Date(c.getTimeInMillis()));
    }


    public static Calendar getCalendar(String date){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(tapjoyDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static String getDateFromDatePicker(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return tapjoyDateFormat.format(c.getTime());
    }

    public static String getLastParsedDate(Context context) {
        String lastParsedDate = Hawk.get(context.getString(R.string.tapjoy_start_date_key), null);
        List<TapjoyDay> tapjoyDays = tapjoyDayDao.queryBuilder().orderDesc(TapjoyDayDao.Properties.Id).limit(1).list();
        if (tapjoyDays.size() > 0)
            lastParsedDate = tapjoyDays.get(0).getDate();
        return lastParsedDate;
    }


    public static long getDaysInDB() {
        return tapjoyDayDao.queryBuilder().count();
    }

    public static long getAppsInDB() {
        return tapjoyAppDao.queryBuilder().count();
    }

    public static void clearDayAndAppTable() {
        tapjoyDayDao.deleteAll();
        tapjoyAppDao.deleteAll();
    }

    public static void deleteDataFromDay(String date){
        //Try to remove apps and day which are on startDate, this to prevent duplicate entries.
        List<TapjoyDay> tapjoyDays = tapjoyDayDao.queryBuilder().where(TapjoyDayDao.Properties.Date.eq(date)).list();
        for (TapjoyDay day : tapjoyDays) {
            tapjoyDayDao.delete(day);
        }
        List<TapjoyApp> tapjoyAppss = tapjoyAppDao.queryBuilder().where(TapjoyAppDao.Properties.Date.eq(date)).list();
        for (TapjoyApp app : tapjoyAppss) {
            tapjoyAppDao.delete(app);
        }
    }

}
