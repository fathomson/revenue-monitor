package sa.revenue.general.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import sa.revenue.general.db.TapjoyDay;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TAPJOY_DAY".
*/
public class TapjoyDayDao extends AbstractDao<TapjoyDay, Long> {

    public static final String TABLENAME = "TAPJOY_DAY";

    /**
     * Properties of entity TapjoyDay.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, String.class, "Date", false, "DATE");
        public final static Property AppCount = new Property(2, Integer.class, "AppCount", false, "APP_COUNT");
        public final static Property PaidInstallsSum = new Property(3, Integer.class, "PaidInstallsSum", false, "PAID_INSTALLS_SUM");
        public final static Property PaidInstallsHourlySum = new Property(4, String.class, "PaidInstallsHourlySum", false, "PAID_INSTALLS_HOURLY_SUM");
        public final static Property PaidClicksSum = new Property(5, Integer.class, "PaidClicksSum", false, "PAID_CLICKS_SUM");
        public final static Property PaidClicksHourlySum = new Property(6, String.class, "PaidClicksHourlySum", false, "PAID_CLICKS_HOURLY_SUM");
        public final static Property SpendSum = new Property(7, Double.class, "SpendSum", false, "SPEND_SUM");
        public final static Property SpendHourlySum = new Property(8, String.class, "SpendHourlySum", false, "SPEND_HOURLY_SUM");
        public final static Property SessionsSum = new Property(9, Integer.class, "SessionsSum", false, "SESSIONS_SUM");
        public final static Property SessionsHourlySum = new Property(10, String.class, "SessionsHourlySum", false, "SESSIONS_HOURLY_SUM");
        public final static Property NewUsersSum = new Property(11, Integer.class, "NewUsersSum", false, "NEW_USERS_SUM");
        public final static Property NewUsersHourlySum = new Property(12, String.class, "NewUsersHourlySum", false, "NEW_USERS_HOURLY_SUM");
        public final static Property DailyActiveUsersSum = new Property(13, Integer.class, "DailyActiveUsersSum", false, "DAILY_ACTIVE_USERS_SUM");
        public final static Property OfferwallRevenueSum = new Property(14, Double.class, "OfferwallRevenueSum", false, "OFFERWALL_REVENUE_SUM");
        public final static Property OfferwallRevenueHourlySum = new Property(15, String.class, "OfferwallRevenueHourlySum", false, "OFFERWALL_REVENUE_HOURLY_SUM");
        public final static Property OfferwallImpressionsSum = new Property(16, Integer.class, "OfferwallImpressionsSum", false, "OFFERWALL_IMPRESSIONS_SUM");
        public final static Property OfferwallImpressionsHourlySum = new Property(17, String.class, "OfferwallImpressionsHourlySum", false, "OFFERWALL_IMPRESSIONS_HOURLY_SUM");
        public final static Property OfferwallClicksSum = new Property(18, Integer.class, "OfferwallClicksSum", false, "OFFERWALL_CLICKS_SUM");
        public final static Property OfferwallClicksHourlySum = new Property(19, String.class, "OfferwallClicksHourlySum", false, "OFFERWALL_CLICKS_HOURLY_SUM");
        public final static Property OfferwallConversionsSum = new Property(20, Integer.class, "OfferwallConversionsSum", false, "OFFERWALL_CONVERSIONS_SUM");
        public final static Property OfferwallConversionsHourlySum = new Property(21, String.class, "OfferwallConversionsHourlySum", false, "OFFERWALL_CONVERSIONS_HOURLY_SUM");
        public final static Property FeaturedOfferRevenueSum = new Property(22, Double.class, "FeaturedOfferRevenueSum", false, "FEATURED_OFFER_REVENUE_SUM");
        public final static Property FeaturedOfferRevenueHourlySum = new Property(23, String.class, "FeaturedOfferRevenueHourlySum", false, "FEATURED_OFFER_REVENUE_HOURLY_SUM");
        public final static Property FeaturedOfferImpressionsSum = new Property(24, Integer.class, "FeaturedOfferImpressionsSum", false, "FEATURED_OFFER_IMPRESSIONS_SUM");
        public final static Property FeaturedOfferImpressionsHourlySum = new Property(25, String.class, "FeaturedOfferImpressionsHourlySum", false, "FEATURED_OFFER_IMPRESSIONS_HOURLY_SUM");
        public final static Property FeaturedOfferClicksSum = new Property(26, Integer.class, "FeaturedOfferClicksSum", false, "FEATURED_OFFER_CLICKS_SUM");
        public final static Property FeaturedOfferClicksHourlySum = new Property(27, String.class, "FeaturedOfferClicksHourlySum", false, "FEATURED_OFFER_CLICKS_HOURLY_SUM");
        public final static Property FeaturedOfferConversionsSum = new Property(28, Integer.class, "FeaturedOfferConversionsSum", false, "FEATURED_OFFER_CONVERSIONS_SUM");
        public final static Property FeaturedOfferConversionsHourlySum = new Property(29, String.class, "FeaturedOfferConversionsHourlySum", false, "FEATURED_OFFER_CONVERSIONS_HOURLY_SUM");
        public final static Property TJMOfferwallRevenueSum = new Property(30, Double.class, "TJMOfferwallRevenueSum", false, "TJMOFFERWALL_REVENUE_SUM");
        public final static Property TJMOfferwallRevenueHourlySum = new Property(31, String.class, "TJMOfferwallRevenueHourlySum", false, "TJMOFFERWALL_REVENUE_HOURLY_SUM");
        public final static Property TJMOfferwallImpressionsSum = new Property(32, Integer.class, "TJMOfferwallImpressionsSum", false, "TJMOFFERWALL_IMPRESSIONS_SUM");
        public final static Property TJMOfferwallImpressionsHourlySum = new Property(33, String.class, "TJMOfferwallImpressionsHourlySum", false, "TJMOFFERWALL_IMPRESSIONS_HOURLY_SUM");
        public final static Property TJMOfferwallClicksSum = new Property(34, Integer.class, "TJMOfferwallClicksSum", false, "TJMOFFERWALL_CLICKS_SUM");
        public final static Property TJMOfferwallClicksHourlySum = new Property(35, String.class, "TJMOfferwallClicksHourlySum", false, "TJMOFFERWALL_CLICKS_HOURLY_SUM");
        public final static Property TJMOfferwallConversionsSum = new Property(36, Integer.class, "TJMOfferwallConversionsSum", false, "TJMOFFERWALL_CONVERSIONS_SUM");
        public final static Property TJMOfferwallConversionsHourlySum = new Property(37, String.class, "TJMOfferwallConversionsHourlySum", false, "TJMOFFERWALL_CONVERSIONS_HOURLY_SUM");
        public final static Property DirectPlayRevenueSum = new Property(38, Double.class, "DirectPlayRevenueSum", false, "DIRECT_PLAY_REVENUE_SUM");
        public final static Property DirectPlayRevenueHourlySum = new Property(39, String.class, "DirectPlayRevenueHourlySum", false, "DIRECT_PLAY_REVENUE_HOURLY_SUM");
        public final static Property DirectPlayImpressionsSum = new Property(40, Integer.class, "DirectPlayImpressionsSum", false, "DIRECT_PLAY_IMPRESSIONS_SUM");
        public final static Property DirectPlayImpressionsHourlySum = new Property(41, String.class, "DirectPlayImpressionsHourlySum", false, "DIRECT_PLAY_IMPRESSIONS_HOURLY_SUM");
        public final static Property DirectPlayClicksSum = new Property(42, Integer.class, "DirectPlayClicksSum", false, "DIRECT_PLAY_CLICKS_SUM");
        public final static Property DirectPlayClicksHourlySum = new Property(43, String.class, "DirectPlayClicksHourlySum", false, "DIRECT_PLAY_CLICKS_HOURLY_SUM");
        public final static Property DirectPlayConversionsSum = new Property(44, Integer.class, "DirectPlayConversionsSum", false, "DIRECT_PLAY_CONVERSIONS_SUM");
        public final static Property DirectPlayConversionsHourlySum = new Property(45, String.class, "DirectPlayConversionsHourlySum", false, "DIRECT_PLAY_CONVERSIONS_HOURLY_SUM");
    };


    public TapjoyDayDao(DaoConfig config) {
        super(config);
    }
    
    public TapjoyDayDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TAPJOY_DAY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATE\" TEXT," + // 1: Date
                "\"APP_COUNT\" INTEGER," + // 2: AppCount
                "\"PAID_INSTALLS_SUM\" INTEGER," + // 3: PaidInstallsSum
                "\"PAID_INSTALLS_HOURLY_SUM\" TEXT," + // 4: PaidInstallsHourlySum
                "\"PAID_CLICKS_SUM\" INTEGER," + // 5: PaidClicksSum
                "\"PAID_CLICKS_HOURLY_SUM\" TEXT," + // 6: PaidClicksHourlySum
                "\"SPEND_SUM\" REAL," + // 7: SpendSum
                "\"SPEND_HOURLY_SUM\" TEXT," + // 8: SpendHourlySum
                "\"SESSIONS_SUM\" INTEGER," + // 9: SessionsSum
                "\"SESSIONS_HOURLY_SUM\" TEXT," + // 10: SessionsHourlySum
                "\"NEW_USERS_SUM\" INTEGER," + // 11: NewUsersSum
                "\"NEW_USERS_HOURLY_SUM\" TEXT," + // 12: NewUsersHourlySum
                "\"DAILY_ACTIVE_USERS_SUM\" INTEGER," + // 13: DailyActiveUsersSum
                "\"OFFERWALL_REVENUE_SUM\" REAL," + // 14: OfferwallRevenueSum
                "\"OFFERWALL_REVENUE_HOURLY_SUM\" TEXT," + // 15: OfferwallRevenueHourlySum
                "\"OFFERWALL_IMPRESSIONS_SUM\" INTEGER," + // 16: OfferwallImpressionsSum
                "\"OFFERWALL_IMPRESSIONS_HOURLY_SUM\" TEXT," + // 17: OfferwallImpressionsHourlySum
                "\"OFFERWALL_CLICKS_SUM\" INTEGER," + // 18: OfferwallClicksSum
                "\"OFFERWALL_CLICKS_HOURLY_SUM\" TEXT," + // 19: OfferwallClicksHourlySum
                "\"OFFERWALL_CONVERSIONS_SUM\" INTEGER," + // 20: OfferwallConversionsSum
                "\"OFFERWALL_CONVERSIONS_HOURLY_SUM\" TEXT," + // 21: OfferwallConversionsHourlySum
                "\"FEATURED_OFFER_REVENUE_SUM\" REAL," + // 22: FeaturedOfferRevenueSum
                "\"FEATURED_OFFER_REVENUE_HOURLY_SUM\" TEXT," + // 23: FeaturedOfferRevenueHourlySum
                "\"FEATURED_OFFER_IMPRESSIONS_SUM\" INTEGER," + // 24: FeaturedOfferImpressionsSum
                "\"FEATURED_OFFER_IMPRESSIONS_HOURLY_SUM\" TEXT," + // 25: FeaturedOfferImpressionsHourlySum
                "\"FEATURED_OFFER_CLICKS_SUM\" INTEGER," + // 26: FeaturedOfferClicksSum
                "\"FEATURED_OFFER_CLICKS_HOURLY_SUM\" TEXT," + // 27: FeaturedOfferClicksHourlySum
                "\"FEATURED_OFFER_CONVERSIONS_SUM\" INTEGER," + // 28: FeaturedOfferConversionsSum
                "\"FEATURED_OFFER_CONVERSIONS_HOURLY_SUM\" TEXT," + // 29: FeaturedOfferConversionsHourlySum
                "\"TJMOFFERWALL_REVENUE_SUM\" REAL," + // 30: TJMOfferwallRevenueSum
                "\"TJMOFFERWALL_REVENUE_HOURLY_SUM\" TEXT," + // 31: TJMOfferwallRevenueHourlySum
                "\"TJMOFFERWALL_IMPRESSIONS_SUM\" INTEGER," + // 32: TJMOfferwallImpressionsSum
                "\"TJMOFFERWALL_IMPRESSIONS_HOURLY_SUM\" TEXT," + // 33: TJMOfferwallImpressionsHourlySum
                "\"TJMOFFERWALL_CLICKS_SUM\" INTEGER," + // 34: TJMOfferwallClicksSum
                "\"TJMOFFERWALL_CLICKS_HOURLY_SUM\" TEXT," + // 35: TJMOfferwallClicksHourlySum
                "\"TJMOFFERWALL_CONVERSIONS_SUM\" INTEGER," + // 36: TJMOfferwallConversionsSum
                "\"TJMOFFERWALL_CONVERSIONS_HOURLY_SUM\" TEXT," + // 37: TJMOfferwallConversionsHourlySum
                "\"DIRECT_PLAY_REVENUE_SUM\" REAL," + // 38: DirectPlayRevenueSum
                "\"DIRECT_PLAY_REVENUE_HOURLY_SUM\" TEXT," + // 39: DirectPlayRevenueHourlySum
                "\"DIRECT_PLAY_IMPRESSIONS_SUM\" INTEGER," + // 40: DirectPlayImpressionsSum
                "\"DIRECT_PLAY_IMPRESSIONS_HOURLY_SUM\" TEXT," + // 41: DirectPlayImpressionsHourlySum
                "\"DIRECT_PLAY_CLICKS_SUM\" INTEGER," + // 42: DirectPlayClicksSum
                "\"DIRECT_PLAY_CLICKS_HOURLY_SUM\" TEXT," + // 43: DirectPlayClicksHourlySum
                "\"DIRECT_PLAY_CONVERSIONS_SUM\" INTEGER," + // 44: DirectPlayConversionsSum
                "\"DIRECT_PLAY_CONVERSIONS_HOURLY_SUM\" TEXT);"); // 45: DirectPlayConversionsHourlySum
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TAPJOY_DAY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TapjoyDay entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Date = entity.getDate();
        if (Date != null) {
            stmt.bindString(2, Date);
        }
 
        Integer AppCount = entity.getAppCount();
        if (AppCount != null) {
            stmt.bindLong(3, AppCount);
        }
 
        Integer PaidInstallsSum = entity.getPaidInstallsSum();
        if (PaidInstallsSum != null) {
            stmt.bindLong(4, PaidInstallsSum);
        }
 
        String PaidInstallsHourlySum = entity.getPaidInstallsHourlySum();
        if (PaidInstallsHourlySum != null) {
            stmt.bindString(5, PaidInstallsHourlySum);
        }
 
        Integer PaidClicksSum = entity.getPaidClicksSum();
        if (PaidClicksSum != null) {
            stmt.bindLong(6, PaidClicksSum);
        }
 
        String PaidClicksHourlySum = entity.getPaidClicksHourlySum();
        if (PaidClicksHourlySum != null) {
            stmt.bindString(7, PaidClicksHourlySum);
        }
 
        Double SpendSum = entity.getSpendSum();
        if (SpendSum != null) {
            stmt.bindDouble(8, SpendSum);
        }
 
        String SpendHourlySum = entity.getSpendHourlySum();
        if (SpendHourlySum != null) {
            stmt.bindString(9, SpendHourlySum);
        }
 
        Integer SessionsSum = entity.getSessionsSum();
        if (SessionsSum != null) {
            stmt.bindLong(10, SessionsSum);
        }
 
        String SessionsHourlySum = entity.getSessionsHourlySum();
        if (SessionsHourlySum != null) {
            stmt.bindString(11, SessionsHourlySum);
        }
 
        Integer NewUsersSum = entity.getNewUsersSum();
        if (NewUsersSum != null) {
            stmt.bindLong(12, NewUsersSum);
        }
 
        String NewUsersHourlySum = entity.getNewUsersHourlySum();
        if (NewUsersHourlySum != null) {
            stmt.bindString(13, NewUsersHourlySum);
        }
 
        Integer DailyActiveUsersSum = entity.getDailyActiveUsersSum();
        if (DailyActiveUsersSum != null) {
            stmt.bindLong(14, DailyActiveUsersSum);
        }
 
        Double OfferwallRevenueSum = entity.getOfferwallRevenueSum();
        if (OfferwallRevenueSum != null) {
            stmt.bindDouble(15, OfferwallRevenueSum);
        }
 
        String OfferwallRevenueHourlySum = entity.getOfferwallRevenueHourlySum();
        if (OfferwallRevenueHourlySum != null) {
            stmt.bindString(16, OfferwallRevenueHourlySum);
        }
 
        Integer OfferwallImpressionsSum = entity.getOfferwallImpressionsSum();
        if (OfferwallImpressionsSum != null) {
            stmt.bindLong(17, OfferwallImpressionsSum);
        }
 
        String OfferwallImpressionsHourlySum = entity.getOfferwallImpressionsHourlySum();
        if (OfferwallImpressionsHourlySum != null) {
            stmt.bindString(18, OfferwallImpressionsHourlySum);
        }
 
        Integer OfferwallClicksSum = entity.getOfferwallClicksSum();
        if (OfferwallClicksSum != null) {
            stmt.bindLong(19, OfferwallClicksSum);
        }
 
        String OfferwallClicksHourlySum = entity.getOfferwallClicksHourlySum();
        if (OfferwallClicksHourlySum != null) {
            stmt.bindString(20, OfferwallClicksHourlySum);
        }
 
        Integer OfferwallConversionsSum = entity.getOfferwallConversionsSum();
        if (OfferwallConversionsSum != null) {
            stmt.bindLong(21, OfferwallConversionsSum);
        }
 
        String OfferwallConversionsHourlySum = entity.getOfferwallConversionsHourlySum();
        if (OfferwallConversionsHourlySum != null) {
            stmt.bindString(22, OfferwallConversionsHourlySum);
        }
 
        Double FeaturedOfferRevenueSum = entity.getFeaturedOfferRevenueSum();
        if (FeaturedOfferRevenueSum != null) {
            stmt.bindDouble(23, FeaturedOfferRevenueSum);
        }
 
        String FeaturedOfferRevenueHourlySum = entity.getFeaturedOfferRevenueHourlySum();
        if (FeaturedOfferRevenueHourlySum != null) {
            stmt.bindString(24, FeaturedOfferRevenueHourlySum);
        }
 
        Integer FeaturedOfferImpressionsSum = entity.getFeaturedOfferImpressionsSum();
        if (FeaturedOfferImpressionsSum != null) {
            stmt.bindLong(25, FeaturedOfferImpressionsSum);
        }
 
        String FeaturedOfferImpressionsHourlySum = entity.getFeaturedOfferImpressionsHourlySum();
        if (FeaturedOfferImpressionsHourlySum != null) {
            stmt.bindString(26, FeaturedOfferImpressionsHourlySum);
        }
 
        Integer FeaturedOfferClicksSum = entity.getFeaturedOfferClicksSum();
        if (FeaturedOfferClicksSum != null) {
            stmt.bindLong(27, FeaturedOfferClicksSum);
        }
 
        String FeaturedOfferClicksHourlySum = entity.getFeaturedOfferClicksHourlySum();
        if (FeaturedOfferClicksHourlySum != null) {
            stmt.bindString(28, FeaturedOfferClicksHourlySum);
        }
 
        Integer FeaturedOfferConversionsSum = entity.getFeaturedOfferConversionsSum();
        if (FeaturedOfferConversionsSum != null) {
            stmt.bindLong(29, FeaturedOfferConversionsSum);
        }
 
        String FeaturedOfferConversionsHourlySum = entity.getFeaturedOfferConversionsHourlySum();
        if (FeaturedOfferConversionsHourlySum != null) {
            stmt.bindString(30, FeaturedOfferConversionsHourlySum);
        }
 
        Double TJMOfferwallRevenueSum = entity.getTJMOfferwallRevenueSum();
        if (TJMOfferwallRevenueSum != null) {
            stmt.bindDouble(31, TJMOfferwallRevenueSum);
        }
 
        String TJMOfferwallRevenueHourlySum = entity.getTJMOfferwallRevenueHourlySum();
        if (TJMOfferwallRevenueHourlySum != null) {
            stmt.bindString(32, TJMOfferwallRevenueHourlySum);
        }
 
        Integer TJMOfferwallImpressionsSum = entity.getTJMOfferwallImpressionsSum();
        if (TJMOfferwallImpressionsSum != null) {
            stmt.bindLong(33, TJMOfferwallImpressionsSum);
        }
 
        String TJMOfferwallImpressionsHourlySum = entity.getTJMOfferwallImpressionsHourlySum();
        if (TJMOfferwallImpressionsHourlySum != null) {
            stmt.bindString(34, TJMOfferwallImpressionsHourlySum);
        }
 
        Integer TJMOfferwallClicksSum = entity.getTJMOfferwallClicksSum();
        if (TJMOfferwallClicksSum != null) {
            stmt.bindLong(35, TJMOfferwallClicksSum);
        }
 
        String TJMOfferwallClicksHourlySum = entity.getTJMOfferwallClicksHourlySum();
        if (TJMOfferwallClicksHourlySum != null) {
            stmt.bindString(36, TJMOfferwallClicksHourlySum);
        }
 
        Integer TJMOfferwallConversionsSum = entity.getTJMOfferwallConversionsSum();
        if (TJMOfferwallConversionsSum != null) {
            stmt.bindLong(37, TJMOfferwallConversionsSum);
        }
 
        String TJMOfferwallConversionsHourlySum = entity.getTJMOfferwallConversionsHourlySum();
        if (TJMOfferwallConversionsHourlySum != null) {
            stmt.bindString(38, TJMOfferwallConversionsHourlySum);
        }
 
        Double DirectPlayRevenueSum = entity.getDirectPlayRevenueSum();
        if (DirectPlayRevenueSum != null) {
            stmt.bindDouble(39, DirectPlayRevenueSum);
        }
 
        String DirectPlayRevenueHourlySum = entity.getDirectPlayRevenueHourlySum();
        if (DirectPlayRevenueHourlySum != null) {
            stmt.bindString(40, DirectPlayRevenueHourlySum);
        }
 
        Integer DirectPlayImpressionsSum = entity.getDirectPlayImpressionsSum();
        if (DirectPlayImpressionsSum != null) {
            stmt.bindLong(41, DirectPlayImpressionsSum);
        }
 
        String DirectPlayImpressionsHourlySum = entity.getDirectPlayImpressionsHourlySum();
        if (DirectPlayImpressionsHourlySum != null) {
            stmt.bindString(42, DirectPlayImpressionsHourlySum);
        }
 
        Integer DirectPlayClicksSum = entity.getDirectPlayClicksSum();
        if (DirectPlayClicksSum != null) {
            stmt.bindLong(43, DirectPlayClicksSum);
        }
 
        String DirectPlayClicksHourlySum = entity.getDirectPlayClicksHourlySum();
        if (DirectPlayClicksHourlySum != null) {
            stmt.bindString(44, DirectPlayClicksHourlySum);
        }
 
        Integer DirectPlayConversionsSum = entity.getDirectPlayConversionsSum();
        if (DirectPlayConversionsSum != null) {
            stmt.bindLong(45, DirectPlayConversionsSum);
        }
 
        String DirectPlayConversionsHourlySum = entity.getDirectPlayConversionsHourlySum();
        if (DirectPlayConversionsHourlySum != null) {
            stmt.bindString(46, DirectPlayConversionsHourlySum);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public TapjoyDay readEntity(Cursor cursor, int offset) {
        TapjoyDay entity = new TapjoyDay( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Date
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // AppCount
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // PaidInstallsSum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // PaidInstallsHourlySum
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // PaidClicksSum
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // PaidClicksHourlySum
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // SpendSum
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // SpendHourlySum
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // SessionsSum
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // SessionsHourlySum
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // NewUsersSum
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // NewUsersHourlySum
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // DailyActiveUsersSum
            cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14), // OfferwallRevenueSum
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // OfferwallRevenueHourlySum
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // OfferwallImpressionsSum
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // OfferwallImpressionsHourlySum
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // OfferwallClicksSum
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // OfferwallClicksHourlySum
            cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // OfferwallConversionsSum
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // OfferwallConversionsHourlySum
            cursor.isNull(offset + 22) ? null : cursor.getDouble(offset + 22), // FeaturedOfferRevenueSum
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // FeaturedOfferRevenueHourlySum
            cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24), // FeaturedOfferImpressionsSum
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // FeaturedOfferImpressionsHourlySum
            cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26), // FeaturedOfferClicksSum
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // FeaturedOfferClicksHourlySum
            cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28), // FeaturedOfferConversionsSum
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // FeaturedOfferConversionsHourlySum
            cursor.isNull(offset + 30) ? null : cursor.getDouble(offset + 30), // TJMOfferwallRevenueSum
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // TJMOfferwallRevenueHourlySum
            cursor.isNull(offset + 32) ? null : cursor.getInt(offset + 32), // TJMOfferwallImpressionsSum
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // TJMOfferwallImpressionsHourlySum
            cursor.isNull(offset + 34) ? null : cursor.getInt(offset + 34), // TJMOfferwallClicksSum
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // TJMOfferwallClicksHourlySum
            cursor.isNull(offset + 36) ? null : cursor.getInt(offset + 36), // TJMOfferwallConversionsSum
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // TJMOfferwallConversionsHourlySum
            cursor.isNull(offset + 38) ? null : cursor.getDouble(offset + 38), // DirectPlayRevenueSum
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // DirectPlayRevenueHourlySum
            cursor.isNull(offset + 40) ? null : cursor.getInt(offset + 40), // DirectPlayImpressionsSum
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // DirectPlayImpressionsHourlySum
            cursor.isNull(offset + 42) ? null : cursor.getInt(offset + 42), // DirectPlayClicksSum
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // DirectPlayClicksHourlySum
            cursor.isNull(offset + 44) ? null : cursor.getInt(offset + 44), // DirectPlayConversionsSum
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45) // DirectPlayConversionsHourlySum
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TapjoyDay entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAppCount(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setPaidInstallsSum(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setPaidInstallsHourlySum(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPaidClicksSum(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setPaidClicksHourlySum(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSpendSum(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setSpendHourlySum(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSessionsSum(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setSessionsHourlySum(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setNewUsersSum(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setNewUsersHourlySum(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setDailyActiveUsersSum(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setOfferwallRevenueSum(cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14));
        entity.setOfferwallRevenueHourlySum(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setOfferwallImpressionsSum(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setOfferwallImpressionsHourlySum(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setOfferwallClicksSum(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setOfferwallClicksHourlySum(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setOfferwallConversionsSum(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setOfferwallConversionsHourlySum(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setFeaturedOfferRevenueSum(cursor.isNull(offset + 22) ? null : cursor.getDouble(offset + 22));
        entity.setFeaturedOfferRevenueHourlySum(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setFeaturedOfferImpressionsSum(cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24));
        entity.setFeaturedOfferImpressionsHourlySum(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setFeaturedOfferClicksSum(cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26));
        entity.setFeaturedOfferClicksHourlySum(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setFeaturedOfferConversionsSum(cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28));
        entity.setFeaturedOfferConversionsHourlySum(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setTJMOfferwallRevenueSum(cursor.isNull(offset + 30) ? null : cursor.getDouble(offset + 30));
        entity.setTJMOfferwallRevenueHourlySum(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setTJMOfferwallImpressionsSum(cursor.isNull(offset + 32) ? null : cursor.getInt(offset + 32));
        entity.setTJMOfferwallImpressionsHourlySum(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setTJMOfferwallClicksSum(cursor.isNull(offset + 34) ? null : cursor.getInt(offset + 34));
        entity.setTJMOfferwallClicksHourlySum(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setTJMOfferwallConversionsSum(cursor.isNull(offset + 36) ? null : cursor.getInt(offset + 36));
        entity.setTJMOfferwallConversionsHourlySum(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setDirectPlayRevenueSum(cursor.isNull(offset + 38) ? null : cursor.getDouble(offset + 38));
        entity.setDirectPlayRevenueHourlySum(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setDirectPlayImpressionsSum(cursor.isNull(offset + 40) ? null : cursor.getInt(offset + 40));
        entity.setDirectPlayImpressionsHourlySum(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setDirectPlayClicksSum(cursor.isNull(offset + 42) ? null : cursor.getInt(offset + 42));
        entity.setDirectPlayClicksHourlySum(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setDirectPlayConversionsSum(cursor.isNull(offset + 44) ? null : cursor.getInt(offset + 44));
        entity.setDirectPlayConversionsHourlySum(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TapjoyDay entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TapjoyDay entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}