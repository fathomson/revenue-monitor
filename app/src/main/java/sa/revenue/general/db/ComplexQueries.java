package sa.revenue.general.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sa.revenue.AppController;
import sa.revenue.general.model.Advertiser;
import sa.revenue.general.model.Day;

/**
 * Created by un on 1/4/2016.
 * Atleast the ones nor supported by greenorm.
 */
public class ComplexQueries {
    private static SQLiteDatabase database = AppController.getInstance().getWritableDatabse();

    public static List<AdPlacement> getAdPlacementsGrouped(String date) {
        List<AdPlacement> adPlacements = new ArrayList<>();
        String query = "select t1.a as advertiser, t1.n as name, t1.r as revenue from (" +
                "select 1 as a, AD_UNIT_NAME as n, sum(EARNINGS) as r from ADMOB_AD_UNIT where date > date('" + date + "') group by n" +
                " union " +
                "select 2 as a, NAME as n, sum(OFFERWALL_REVENUE) as r from TAPJOY_APP where date > date('" + date + "') group by n" +
                " order by a asc, r desc ) t1";
        Cursor cursor = database.rawQuery(query, null);
        //  Log.i("getAdPlacementsGrouped", DatabaseUtils.dumpCursorToString(cursor));

        if (cursor != null && !cursor.isAfterLast()) {
            while (cursor.moveToNext()) {
                AdPlacement adPlacement = new AdPlacement();
                switch (Integer.parseInt(cursor.getString(cursor.getColumnIndex("advertiser")))) {
                    case 1:
                        adPlacement.setAdCompany(Advertiser.ADMOB);
                        break;
                    case 2: {
                        adPlacement.setAdCompany(Advertiser.TAPJOY);
                        break;
                    }
                }
                adPlacement.setAdvertiserAdName(cursor.getString(cursor.getColumnIndex("name")));
                adPlacement.setRevenue(cursor.getDouble(cursor.getColumnIndex("revenue")));
                adPlacements.add(adPlacement);
            }
        }
        return adPlacements;
    }

    //TODO - rewrite query someday?
    public static List<Day> getDailyRevenue(String date) {
        List<Day> days = new ArrayList<>();
        String query = "select t1.d as date, sum(t1.admob) as admob, sum(t1.tapjoy) as tapjoy from (" +
                "select DATE as d, EARNINGS as admob, 0 as tapjoy from ADMOB_DAY where date > ('" + date + "')" +
                " union " +
                "select DATE as d,0 as admob, OFFERWALL_REVENUE_SUM as tapjoy from TAPJOY_DAY where date > date('" + date + "') " +
                ") t1 group by date order by date asc ";
        Cursor cursor = database.rawQuery(query, null);
        //   Log.i("getDailyRevenue", DatabaseUtils.dumpCursorToString(cursor));
        if (cursor != null && !cursor.isAfterLast()) {
            while (cursor.moveToNext()) {
                Day day = new Day();
                day.setDate(cursor.getString(cursor.getColumnIndex("date")));
                day.setAdmob(cursor.getDouble(cursor.getColumnIndex("admob")));
                day.setTapjoy(cursor.getDouble(cursor.getColumnIndex("tapjoy")));
                days.add(day);

            }
        }

        return days;
    }

    public static String getFirstDate() {
        String firstDate = "2016-01-01";
        String query = "select t1.date from (" +
                "select date from ADMOB_DAY " +
                " union " +
                "select date from TAPJOY_DAY " +
                " order by date asc ) t1 limit 1";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && !cursor.isAfterLast()) {
            while (cursor.moveToNext()) {
                firstDate = cursor.getString(cursor.getColumnIndex("date"));
            }
        }
        return firstDate;
    }
}
