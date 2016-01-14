package sa.revenue.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import sa.revenue.AppController;
import sa.revenue.R;
import sa.revenue.Utils;
import sa.revenue.advertisers.admob.AdmobUtils;
import sa.revenue.advertisers.tapjoy.TapjoyUtils;
import sa.revenue.general.adapter.AdPlacementDetailsArrayAdapter;
import sa.revenue.general.db.AdPlacement;
import sa.revenue.general.db.ComplexQueries;
import sa.revenue.general.model.Day;
import sa.revenue.general.model.Message;
import sa.revenue.general.model.RevenueModel;
import sa.revenue.general.view.TransformLayoutWithHeader;


public class RevenueFragment extends Fragment {
    private int periodSelected = 1;

    String TAG = RevenueFragment.class.getSimpleName();
    private Context globalContext = null;

    @Bind(R.id.parallaxScrollView)
    ParallaxScrollView parallaxScrollView;

    @Bind(R.id.progressBar_revenueChartLoading)
    ProgressBar progressBar_revenueChartLoading;

    @Bind(R.id.textView_revenueSum)
    TextView textView_revenueSum;

    @Bind(R.id.lineChart_Revenue)
    LineChart lineChart_Revenue;

    //TransformLayouts for advertisers
    @Bind(R.id.transformLayout_admob)
    TransformLayoutWithHeader transformLayout_admob;

    @Bind(R.id.transformLayout_tapjoy)
    TransformLayoutWithHeader transformLayout_tapjoy;

    //Views transform layouts
    AdPlacementDetailsArrayAdapter admobDetailsAdapter;
    ListView listView_admobDetails;

    AdPlacementDetailsArrayAdapter tapjoyDetailsAdapter;
    ListView listView_tapjoyDetails;


    public RevenueFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        globalContext = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_revenue, container, false);
        ButterKnife.bind(this, rootView);
        prepareTransformLayoutViews();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new calculateRevenueTask(periodSelected).execute();
        updateLoading();

    }


    private void prepareTransformLayoutViews() {


        transformLayout_admob.setVisibility(AdmobUtils.hasData() ? View.VISIBLE : View.INVISIBLE);
        listView_admobDetails = (ListView) transformLayout_admob.getDetailsView().findViewById(R.id.listView_admobAdUnitDetails);
        admobDetailsAdapter = new AdPlacementDetailsArrayAdapter(globalContext, new ArrayList<AdPlacement>());
        listView_admobDetails.setAdapter(admobDetailsAdapter);


        transformLayout_tapjoy.setVisibility(TapjoyUtils.hasData() ? View.VISIBLE : View.INVISIBLE);
        listView_tapjoyDetails = (ListView) transformLayout_tapjoy.getDetailsView().findViewById(R.id.listView_tapjoyAppDetails);
        tapjoyDetailsAdapter = new AdPlacementDetailsArrayAdapter(globalContext, new ArrayList<AdPlacement>());
        listView_tapjoyDetails.setAdapter(tapjoyDetailsAdapter);


    }

    private void updateLoading() {
        if (AdmobUtils.hasData())
            transformLayout_admob.setLoading(true);
        if (TapjoyUtils.hasData())
            transformLayout_tapjoy.setLoading(true);

    }

    public static void getTotalHeightOfListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += mView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }


    private class calculateRevenueTask extends AsyncTask<Void, Void, RevenueModel> {
        int position;

        private calculateRevenueTask(int position) {
            this.position = position;
        }

        @Override
        protected void onPreExecute() {
            showLoadingRevenue(true);
            lineChart_Revenue.clear();
        }

        @Override
        protected RevenueModel doInBackground(Void... params) {

            RevenueModel revenue = null;
            try {
                String date = Utils.getDateXdaysAgo(getDays(position));
                revenue = calculateAdPlacementRevenue(date);
                populateChart(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return revenue;
        }

        @Override
        protected void onPostExecute(RevenueModel revenue) {
            lineChart_Revenue.fitScreen();
            lineChart_Revenue.animateX(1500);
            //Set header to total revenue in period
            textView_revenueSum.setText(revenue.getTotalRevenue());

            //Set headers of advertiser layouts to advertiser revenue in period
            transformLayout_admob.addRevenueToHeader(revenue.getAdmobRevenue());
            transformLayout_tapjoy.addRevenueToHeader(revenue.getTapjoyRevenue());

            //Message detail view per advetiser;
            admobDetailsAdapter.swap(revenue.getAdmobAdPlacements());
            tapjoyDetailsAdapter.swap(revenue.getTapjoyAdPlacements());

            getTotalHeightOfListView(listView_admobDetails);
            getTotalHeightOfListView(listView_tapjoyDetails);
            showLoadingRevenue(false);
        }
    }

    private void showLoadingRevenue(boolean showProgressBar) {
        lineChart_Revenue.setVisibility(showProgressBar ? View.INVISIBLE : View.VISIBLE);
        progressBar_revenueChartLoading.setVisibility(showProgressBar ? View.VISIBLE : View.INVISIBLE);
    }

    private RevenueModel calculateAdPlacementRevenue(String date) {
        RevenueModel revenueModel = new RevenueModel();
        //Calculate values fro transformlayouts
        List<AdPlacement> admobAdPlacements = new ArrayList<>();
        double admobRevenue = 0;
        List<AdPlacement> tapjoyAdPlacements = new ArrayList<>();
        double tapjoyRevenue = 0;

        //Adplacements for all advertisers
        List<AdPlacement> adPlacements = ComplexQueries.getAdPlacementsGrouped(date);
        Iterator<AdPlacement> adPlacementIterator = adPlacements.iterator();
        while (adPlacementIterator.hasNext()) {
            AdPlacement adPlacement = adPlacementIterator.next();
            switch (adPlacement.getAdCompany()) {
                case ADMOB:
                    admobAdPlacements.add(adPlacement);
                    admobRevenue = admobRevenue + adPlacement.getRevenue();
                    break;
                case TAPJOY:
                    tapjoyAdPlacements.add(adPlacement);
                    tapjoyRevenue = tapjoyRevenue + adPlacement.getRevenue();
                    break;
            }
        }

        double totalRevenue = admobRevenue + tapjoyRevenue;
        revenueModel.setTotalRevenue(Utils.formatDoubleAsCurreny(totalRevenue));
        revenueModel.setAdmobRevenue(Utils.formatDoubleAsCurreny(admobRevenue));
        revenueModel.setAdmobAdPlacements(admobAdPlacements);
        revenueModel.setTapjoyRevenue(Utils.formatDoubleAsCurreny(tapjoyRevenue));
        revenueModel.setTapjoyAdPlacements(tapjoyAdPlacements);
        return revenueModel;


    }


    private void populateChart(String date) {
        List<Day> days = ComplexQueries.getDailyRevenue(date);

        //Configure chart
        lineChart_Revenue.setDescription("");
        lineChart_Revenue.setDrawGridBackground(false);
        lineChart_Revenue.setTouchEnabled(true);
        lineChart_Revenue.setDragEnabled(true);
        lineChart_Revenue.setScaleXEnabled(true);
        lineChart_Revenue.setScaleYEnabled(true);
        lineChart_Revenue.setPinchZoom(false);

        //disable legend
        Legend legend = lineChart_Revenue.getLegend();
        legend.setEnabled(false);

        //Disable axes
        lineChart_Revenue.getAxisLeft().setEnabled(false);
        lineChart_Revenue.getAxisRight().setEnabled(false);

        //set xaxis
        XAxis xAxis = lineChart_Revenue.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(ContextCompat.getColor(globalContext, R.color.primary_dark));


        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> admobYVals = new ArrayList<>();
        ArrayList<Entry> tapjoyYVals = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            xVals.add(days.get(i).getDate());
            admobYVals.add(new Entry(Float.parseFloat(String.format("%.2f", days.get(i).getAdmob())), i));
            tapjoyYVals.add(new Entry(Float.parseFloat(String.format("%.2f", days.get(i).getTapjoy())), i));
        }


        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        if (AdmobUtils.hasData()) {
            LineDataSet admobRevenueDataSet = new LineDataSet(admobYVals, "Admob");
            admobRevenueDataSet.setColor(AppController.getInstance().getDefaultSharedPreferences().getInt(getString(R.string.admob_color_key), ContextCompat.getColor(globalContext, R.color.admob)));
            admobRevenueDataSet.setLineWidth(2f);
            admobRevenueDataSet.setCircleColor(ContextCompat.getColor(globalContext, R.color.primary_dark));
            dataSets.add(admobRevenueDataSet);
        }

        if (TapjoyUtils.hasData()) {
            LineDataSet tapjoyRevenueDataSet = new LineDataSet(tapjoyYVals, "Tapjoy");
            tapjoyRevenueDataSet.setColor(AppController.getInstance().getDefaultSharedPreferences().getInt(getString(R.string.tapjoy_color_key), ContextCompat.getColor(globalContext, R.color.tapjoy)));
            tapjoyRevenueDataSet.setLineWidth(2f);
            tapjoyRevenueDataSet.setCircleColor(ContextCompat.getColor(globalContext, R.color.primary_dark));
            dataSets.add(tapjoyRevenueDataSet);
        }

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        lineChart_Revenue.setData(data);
        lineChart_Revenue.setViewPortOffsets(90f, 50f, 90f, 0);
        data.setValueTextColor(ContextCompat.getColor(globalContext, R.color.primary_dark));
        data.setValueTextSize(12f);

    }

    private int getDays(int position) {
        int numberOfDays = 1;
        switch (position) {
            case 0:
                //Today - only one day
                break;
            case 1:
                //This week - current day of week
                numberOfDays = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                break;
            case 2:
                //This month - current day of month
                numberOfDays = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                break;
            case 3:
                //This year - current day of week
                numberOfDays = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
                break;
            case 4:
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar fromDay = Calendar.getInstance();
                    fromDay.setTime(sdf.parse(ComplexQueries.getFirstDate()));
                    Calendar toDay = Calendar.getInstance();
                    long diff = toDay.getTimeInMillis() - fromDay.getTimeInMillis();
                    numberOfDays = (int) TimeUnit.MILLISECONDS.toDays(diff);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
        return numberOfDays;
    }


    private void sendScroll() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        parallaxScrollView.fullScroll(View.FOCUS_UP);
                    }
                });
            }
        }).start();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                updateLoading();
                Utils.checkAndUpdateData(globalContext);
                break;
            case R.id.action_today:
                periodSelected = 0;
                break;
            case R.id.action_thisweek:
                periodSelected = 1;
                break;
            case R.id.action_thismonth:
                periodSelected = 2;
                break;
            case R.id.action_thisyear:
                periodSelected = 3;
                break;
            case R.id.action_alltime:
                periodSelected = 4;
                break;

        }
        new calculateRevenueTask(periodSelected).execute();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            //When init and when has parsed today date.
            if (AdmobUtils.getLastParsedDate(globalContext) == null || Utils.todayString(AdmobUtils.admobDateFormat).equals(AdmobUtils.getLastParsedDate(globalContext))) {
                transformLayout_admob.setLoading(false);
            }
            if (TapjoyUtils.getLastParsedDate(globalContext) == null || Utils.todayString(TapjoyUtils.tapjoyDateFormat).equals(TapjoyUtils.getLastParsedDate(globalContext))) {
                transformLayout_tapjoy.setLoading(false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    // Called in Android UI's main thread
    public void onEventMainThread(Message message) {
        switch (message.getAdvertiser()) {
            case ADMOB:
                switch (message.getType()) {
                    case PARSING_DONE:
                        transformLayout_admob.setLoading(false);
                        break;
                }
                break;
            case TAPJOY:
                switch (message.getType()) {
                    case PARSING_NEXT_DAY:
                        new calculateRevenueTask(periodSelected).execute();
                        break;
                    case PARSING_DONE:
                        transformLayout_tapjoy.setLoading(false);
                        break;
                }
                break;
            default:
                break;
        }
    }


}
