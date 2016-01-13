package sa.revenue;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.securepreferences.SecurePreferences;

import java.util.Map;

/**
 * Created by un on 1/7/2016.
 */
public class InitActivity extends AppCompatActivity {
    private String password;
    private SharedPreferences mSecurePrefs;
    private SharedPreferences.Editor mSecureEditor;

    private String passwordHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        mSecurePrefs = AppController.getInstance().getSecureSharedPreferences();
        passwordHash = SecurePreferences.hashPrefKey(getString(R.string.secure_password_key));

        for (Map.Entry<String, ?> entry : mSecurePrefs.getAll().entrySet()) {
            final String key = entry.getKey();
            if (key == null) {
                continue;
            } else if (key.equals(passwordHash)) {
                password = mSecurePrefs.getString(getString(R.string.secure_password_key),null);
            }
        }


        mSecureEditor = mSecurePrefs.edit();
        if (password == null) {
            final AlertDialog alertDialog = new AlertDialog.Builder(InitActivity.this).create();
            alertDialog.setTitle(getString(R.string.dialog_password_title));
            alertDialog.setMessage(getString(R.string.dialog_password_message));
            LayoutInflater inflater = LayoutInflater.from(this);
            View view_password = inflater.inflate(R.layout.dialog_set_password, null);
            final EditText editText_password = (EditText) view_password.findViewById(R.id.editText_password);
            alertDialog.setView(view_password);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText_password.getText().length() > 2) {
                        password = editText_password.getText().toString();
                        mSecureEditor.putString(getString(R.string.secure_password_key),password).commit();
                        alertDialog.dismiss();

                        InitHawkTask initHawkTask = new InitHawkTask();
                        initHawkTask.execute();
                    } else {
                        editText_password.setError("Atleast 3 characters");
                    }

                }
            });
        } else {
            InitHawkTask initHawkTask = new InitHawkTask();
            initHawkTask.execute();

        }


    }


    private class InitHawkTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            Hawk.init(InitActivity.this)
                    .setEncryptionMethod(HawkBuilder.EncryptionMethod.HIGHEST)
                    .setPassword(password)
                    .setStorage(HawkBuilder.newSqliteStorage(InitActivity.this))
                    .setLogLevel(LogLevel.FULL)
                    .build();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(InitActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
