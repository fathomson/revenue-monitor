package sa.revenue;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.securepreferences.SecurePreferences;

import de.greenrobot.dao.query.QueryBuilder;
import sa.revenue.general.db.DaoMaster;
import sa.revenue.general.db.DaoSession;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    SQLiteDatabase db;
    public DaoSession daoSession;
    private SecurePreferences mUserPrefs;
    private SecurePreferences mSecurePrefs;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "revenue-db", null);
        db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }


    // Volley
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        db.close();
        db = null;
    }

    public SQLiteDatabase getWritableDatabse() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * Single point for the app to get the secure prefs object
     * @return
     */
    public SharedPreferences getSecureSharedPreferences() {
        if(mSecurePrefs==null){
            mSecurePrefs = new SecurePreferences(this, "", "revenue-sp");
            SecurePreferences.setLoggingEnabled(true);
        }
        return mSecurePrefs;
    }

    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}
