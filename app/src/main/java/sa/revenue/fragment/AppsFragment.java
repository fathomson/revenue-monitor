package sa.revenue.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sa.revenue.AppController;
import sa.revenue.R;
import sa.revenue.general.db.AppDao;
import sa.revenue.general.db.DaoSession;


public class AppsFragment extends Fragment {
    private DaoSession daoSession;
    private AppDao appDao;


    String TAG = AppsFragment.class.getSimpleName();
    private Context globalContext = null;

    @Bind(R.id.listView_apps)
    ListView listView_apps;

    @Bind(R.id.floatingActionButton_advertisers)
    FloatingActionButton floatingActionButton_advertisers;

    public AppsFragment() {

    }

    //TODO - rewrite adapter and update when new item added.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalContext = this.getActivity();
        daoSession = AppController.getInstance().getDaoSession();
        appDao = daoSession.getAppDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Defined Array values to show in ListView
        //    updateAppsList();


        floatingActionButton_advertisers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  addAppDialog();
            }
        });

    }


    private void addAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(globalContext, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(R.string.add_app));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_app, null);
        builder.setView(dialogView);

        final EditText editText = (EditText) dialogView.findViewById(R.id.editText_appName);
        //     final Spinner tapjoyApps = (Spinner) dialogView.findViewById(R.id.spinner_tapjoyApps);
        //    tapjoyApps.setAdapter(new AppSpinnerAdapter(globalContext, ComplexQueries.getTapjoyAppsAsApps()));

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO - write neat validator
                /*
                   if (editText.getText().length() > 2) {
                      App app = (App) tapjoyApps.getSelectedItem();
                      app.setName(editText.getText().toString());
                      appDao.insert(app);

                       updateAppsList();
                  } else {
                      Toast.makeText(globalContext, "name atleast 3 chars", Toast.LENGTH_SHORT).show();
                  }*/
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
/*
    private void updateAppDialog(final App app) {
        AlertDialog.Builder builder = new AlertDialog.Builder(globalContext, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getString(R.string.update_app));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_app, null);
        builder.setView(dialogView);

        final EditText editText = (EditText) dialogView.findViewById(R.id.editText_appName);
        editText.setText(app.getName());

        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_tapjoyApps);
        List<App> apps = ComplexQueries.getTapjoyAppsAsApps();

        int pos = 0;
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getAdvertiserAdId() != null)
                if (apps.get(i) == app) {
                    pos = i;
                    break;
                }
        }

        spinner.setAdapter(new AppSpinnerAdapter(globalContext, apps));
        spinner.setSelection(pos);


        builder.setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // appAds.delete();
                //  updateAppsList();
            }
        });
        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO - write neat validator

                if (editText.getText().length() > 2) {
                    App app = (App) spinner.getSelectedItem();
                    app.setName(editText.getText().toString());

                    appDao.update(app);

                    updateAppsList();

                } else {
                    Toast.makeText(globalContext, "name atleast 3 chars", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.show();
    }


    private void updateAppsList() {
        final List<App> appAds = appDao.loadAll();

        AppSpinnerAdapter adapter = new AppSpinnerAdapter(globalContext, appAds);
        listView_apps.setAdapter(adapter);
        listView_apps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateAppDialog(appAds.get(position));
            }
        });

    }
    */


}
