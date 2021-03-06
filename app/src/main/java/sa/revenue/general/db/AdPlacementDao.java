package sa.revenue.general.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import sa.revenue.general.db.AdPlacement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "AD_PLACEMENT".
*/
public class AdPlacementDao extends AbstractDao<AdPlacement, Long> {

    public static final String TABLENAME = "AD_PLACEMENT";

    /**
     * Properties of entity AdPlacement.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Advertiser = new Property(1, String.class, "Advertiser", false, "ADVERTISER");
        public final static Property AdvertiserAdId = new Property(2, String.class, "AdvertiserAdId", false, "ADVERTISER_AD_ID");
        public final static Property AdvertiserAdName = new Property(3, String.class, "AdvertiserAdName", false, "ADVERTISER_AD_NAME");
        public final static Property AppId = new Property(4, long.class, "AppId", false, "APP_ID");
    };

    private DaoSession daoSession;

    private Query<AdPlacement> app_AdPlacementsQuery;

    public AdPlacementDao(DaoConfig config) {
        super(config);
    }
    
    public AdPlacementDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"AD_PLACEMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ADVERTISER\" TEXT," + // 1: Advertiser
                "\"ADVERTISER_AD_ID\" TEXT," + // 2: AdvertiserAdId
                "\"ADVERTISER_AD_NAME\" TEXT," + // 3: AdvertiserAdName
                "\"APP_ID\" INTEGER NOT NULL );"); // 4: AppId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AD_PLACEMENT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, AdPlacement entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Advertiser = entity.getAdvertiser();
        if (Advertiser != null) {
            stmt.bindString(2, Advertiser);
        }
 
        String AdvertiserAdId = entity.getAdvertiserAdId();
        if (AdvertiserAdId != null) {
            stmt.bindString(3, AdvertiserAdId);
        }
 
        String AdvertiserAdName = entity.getAdvertiserAdName();
        if (AdvertiserAdName != null) {
            stmt.bindString(4, AdvertiserAdName);
        }
        stmt.bindLong(5, entity.getAppId());
    }

    @Override
    protected void attachEntity(AdPlacement entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public AdPlacement readEntity(Cursor cursor, int offset) {
        AdPlacement entity = new AdPlacement( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Advertiser
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // AdvertiserAdId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // AdvertiserAdName
            cursor.getLong(offset + 4) // AppId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, AdPlacement entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAdvertiser(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAdvertiserAdId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAdvertiserAdName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAppId(cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(AdPlacement entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(AdPlacement entity) {
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
    
    /** Internal query to resolve the "AdPlacements" to-many relationship of App. */
    public List<AdPlacement> _queryApp_AdPlacements(long AppId) {
        synchronized (this) {
            if (app_AdPlacementsQuery == null) {
                QueryBuilder<AdPlacement> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AppId.eq(null));
                app_AdPlacementsQuery = queryBuilder.build();
            }
        }
        Query<AdPlacement> query = app_AdPlacementsQuery.forCurrentThread();
        query.setParameter(0, AppId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getAppDao().getAllColumns());
            builder.append(" FROM AD_PLACEMENT T");
            builder.append(" LEFT JOIN APP T0 ON T.\"APP_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected AdPlacement loadCurrentDeep(Cursor cursor, boolean lock) {
        AdPlacement entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        App app = loadCurrentOther(daoSession.getAppDao(), cursor, offset);
         if(app != null) {
            entity.setApp(app);
        }

        return entity;    
    }

    public AdPlacement loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<AdPlacement> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<AdPlacement> list = new ArrayList<AdPlacement>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<AdPlacement> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<AdPlacement> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
