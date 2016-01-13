package sa.revenue;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Created by gaku on 1/9/14.
 */
public class MyDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "sa.revenue.general.db");
        schema.enableKeepSectionsByDefault();
        addSocket(schema);
        new DaoGenerator().generateAll(schema, ".");
    }

    private static void addSocket(Schema schema) {
        //One app as in store
        Entity app = schema.addEntity("App");
        app.addIdProperty();
        app.addStringProperty("Name");

        //Multiple ads of multiple advertisers which can be in app
        //TODO - create entity for Advertiser ? - Now securely stored in Hawk.
        Entity adPlacement = schema.addEntity("AdPlacement");
        adPlacement.addIdProperty();
        adPlacement.addStringProperty("Advertiser");
        adPlacement.addStringProperty("AdvertiserAdId");
        adPlacement.addStringProperty("AdvertiserAdName");

        //Adplacement can be linked to one App
        Property appId = adPlacement.addLongProperty("AppId").notNull().getProperty();
        adPlacement.addToOne(app, appId);

        //App can have multiple adplacements, of same or different advertisers
        ToMany appToAdPlacement = app.addToMany(adPlacement, appId);
        appToAdPlacement.setName("AdPlacements");

        //AdPlacement results are fetched per day. The metrics differ per advertiser, so link the daily advertisers placement stats to the AdPlacement.
        plugTapjoy(schema, adPlacement);
        plugAdmob(schema, adPlacement);
    }

    private static void plugTapjoy(Schema schema, Entity adPlacement) {
        Entity tapjoy_app = schema.addEntity("TapjoyApp");
        tapjoy_app.addIdProperty();

        //Tapjoy app can be linked to one AdPlacement
        Property adPlacementId = tapjoy_app.addLongProperty("AdPlacementId").notNull().getProperty();
        tapjoy_app.addToOne(adPlacement, adPlacementId);

        //AdPlacement has multiple tapjoy app daily results
        ToMany adPlacementToTapjoy = adPlacement.addToMany(tapjoy_app, adPlacementId);
        adPlacementToTapjoy.setName("TapjoyResults");

        tapjoy_app.addStringProperty("Date");
        tapjoy_app.addStringProperty("Name"); //Seems to be dupe to AppName .
        tapjoy_app.addStringProperty("AppStoreID"); //Can be null -
        tapjoy_app.addStringProperty("AppName"); //AdvertiserAdName - Could be removed [dupe]
        tapjoy_app.addStringProperty("AppKey"); //AdvertiserAdId - Could be removed [dupe]
        tapjoy_app.addStringProperty("url");
        tapjoy_app.addStringProperty("Platform");
        tapjoy_app.addBooleanProperty("Rewarded");
        tapjoy_app.addStringProperty("OfferType");
        tapjoy_app.addIntProperty("PaidInstalls");
        tapjoy_app.addStringProperty("PaidInstallsHourly");
        tapjoy_app.addIntProperty("PaidClicks");
        tapjoy_app.addStringProperty("PaidClicksHourly");
        tapjoy_app.addDoubleProperty("Spend");
        tapjoy_app.addStringProperty("SpendHourly");
        tapjoy_app.addIntProperty("Sessions");
        tapjoy_app.addStringProperty("SessionsHourly");
        tapjoy_app.addIntProperty("NewUsers");
        tapjoy_app.addStringProperty("NewUsersHourly");
        tapjoy_app.addIntProperty("DailyActiveUsers");
        tapjoy_app.addDoubleProperty("OfferwallRevenue");
        tapjoy_app.addStringProperty("OfferwallRevenueHourly");
        tapjoy_app.addIntProperty("OfferwallImpressions");
        tapjoy_app.addStringProperty("OfferwallImpressionsHourly");
        tapjoy_app.addIntProperty("OfferwallClicks");
        tapjoy_app.addStringProperty("OfferwallClicksHourly");
        tapjoy_app.addIntProperty("OfferwallConversions");
        tapjoy_app.addStringProperty("OfferwallConversionsHourly");
        tapjoy_app.addDoubleProperty("FeaturedOfferRevenue");
        tapjoy_app.addStringProperty("FeaturedOfferRevenueHourly");
        tapjoy_app.addIntProperty("FeaturedOfferImpressions");
        tapjoy_app.addStringProperty("FeaturedOfferImpressionsHourly");
        tapjoy_app.addIntProperty("FeaturedOfferClicks");
        tapjoy_app.addStringProperty("FeaturedOfferClicksHourly");
        tapjoy_app.addIntProperty("FeaturedOfferConversions");
        tapjoy_app.addStringProperty("FeaturedOfferConversionsHourly");
        tapjoy_app.addDoubleProperty("TJMOfferwallRevenue");
        tapjoy_app.addStringProperty("TJMOfferwallRevenueHourly");
        tapjoy_app.addIntProperty("TJMOfferwallImpressions");
        tapjoy_app.addStringProperty("TJMOfferwallImpressionsHourly");
        tapjoy_app.addIntProperty("TJMOfferwallClicks");
        tapjoy_app.addStringProperty("TJMOfferwallClicksHourly");
        tapjoy_app.addIntProperty("TJMOfferwallConversions");
        tapjoy_app.addStringProperty("TJMOfferwallConversionsHourly");
        tapjoy_app.addDoubleProperty("DirectPlayRevenue");
        tapjoy_app.addStringProperty("DirectPlayRevenueHourly");
        tapjoy_app.addIntProperty("DirectPlayImpressions");
        tapjoy_app.addStringProperty("DirectPlayImpressionsHourly");
        tapjoy_app.addIntProperty("DirectPlayClicks");
        tapjoy_app.addStringProperty("DirectPlayClicksHourly");
        tapjoy_app.addIntProperty("DirectPlayConversions");
        tapjoy_app.addStringProperty("DirectPlayConversionsHourly");


        //Daily results for tapjoy
        Entity tapjoy_day = schema.addEntity("TapjoyDay");
        tapjoy_day.addIdProperty();
        tapjoy_day.addStringProperty("Date");
        tapjoy_day.addIntProperty("AppCount");
        tapjoy_day.addIntProperty("PaidInstallsSum");
        tapjoy_day.addStringProperty("PaidInstallsHourlySum");
        tapjoy_day.addIntProperty("PaidClicksSum");
        tapjoy_day.addStringProperty("PaidClicksHourlySum");
        tapjoy_day.addDoubleProperty("SpendSum");
        tapjoy_day.addStringProperty("SpendHourlySum");
        tapjoy_day.addIntProperty("SessionsSum");
        tapjoy_day.addStringProperty("SessionsHourlySum");
        tapjoy_day.addIntProperty("NewUsersSum");
        tapjoy_day.addStringProperty("NewUsersHourlySum");
        tapjoy_day.addIntProperty("DailyActiveUsersSum");
        tapjoy_day.addDoubleProperty("OfferwallRevenueSum");
        tapjoy_day.addStringProperty("OfferwallRevenueHourlySum");
        tapjoy_day.addIntProperty("OfferwallImpressionsSum");
        tapjoy_day.addStringProperty("OfferwallImpressionsHourlySum");
        tapjoy_day.addIntProperty("OfferwallClicksSum");
        tapjoy_day.addStringProperty("OfferwallClicksHourlySum");
        tapjoy_day.addIntProperty("OfferwallConversionsSum");
        tapjoy_day.addStringProperty("OfferwallConversionsHourlySum");
        tapjoy_day.addDoubleProperty("FeaturedOfferRevenueSum");
        tapjoy_day.addStringProperty("FeaturedOfferRevenueHourlySum");
        tapjoy_day.addIntProperty("FeaturedOfferImpressionsSum");
        tapjoy_day.addStringProperty("FeaturedOfferImpressionsHourlySum");
        tapjoy_day.addIntProperty("FeaturedOfferClicksSum");
        tapjoy_day.addStringProperty("FeaturedOfferClicksHourlySum");
        tapjoy_day.addIntProperty("FeaturedOfferConversionsSum");
        tapjoy_day.addStringProperty("FeaturedOfferConversionsHourlySum");
        tapjoy_day.addDoubleProperty("TJMOfferwallRevenueSum");
        tapjoy_day.addStringProperty("TJMOfferwallRevenueHourlySum");
        tapjoy_day.addIntProperty("TJMOfferwallImpressionsSum");
        tapjoy_day.addStringProperty("TJMOfferwallImpressionsHourlySum");
        tapjoy_day.addIntProperty("TJMOfferwallClicksSum");
        tapjoy_day.addStringProperty("TJMOfferwallClicksHourlySum");
        tapjoy_day.addIntProperty("TJMOfferwallConversionsSum");
        tapjoy_day.addStringProperty("TJMOfferwallConversionsHourlySum");
        tapjoy_day.addDoubleProperty("DirectPlayRevenueSum");
        tapjoy_day.addStringProperty("DirectPlayRevenueHourlySum");
        tapjoy_day.addIntProperty("DirectPlayImpressionsSum");
        tapjoy_day.addStringProperty("DirectPlayImpressionsHourlySum");
        tapjoy_day.addIntProperty("DirectPlayClicksSum");
        tapjoy_day.addStringProperty("DirectPlayClicksHourlySum");
        tapjoy_day.addIntProperty("DirectPlayConversionsSum");
        tapjoy_day.addStringProperty("DirectPlayConversionsHourlySum");
    }

    private static void plugAdmob(Schema schema, Entity adPlacement) {
        Entity admob_ad_unit = schema.addEntity("AdmobAdUnit");
        admob_ad_unit.addIdProperty();
        //Tapjoy app can be linked to one AdPlacement
        Property adPlacementId = admob_ad_unit.addLongProperty("AdPlacementId").notNull().getProperty();
        admob_ad_unit.addToOne(adPlacement, adPlacementId);

        //AdPlacement has multiple tapjoy app daily results
        ToMany adPlacementToTapjoy = adPlacement.addToMany(admob_ad_unit, adPlacementId);
        adPlacementToTapjoy.setName("AdmobResults");

        admob_ad_unit.addStringProperty("AdClientId"); //multiple account support?
        admob_ad_unit.addStringProperty("AdUnitCode"); //Multuiple account support - not unique?
        admob_ad_unit.addStringProperty("AdUnitId"); //AdvertiserAdId - Could be removed [dupe]
        admob_ad_unit.addStringProperty("AdUnitName"); //AdvertiserAdName -- could be removed [dupe]
        admob_ad_unit.addStringProperty("Date");
        admob_ad_unit.addStringProperty("AdRequests");
        admob_ad_unit.addStringProperty("AdRequestsCoverage");
        admob_ad_unit.addStringProperty("Clicks");
        admob_ad_unit.addStringProperty("CostPerClick");
        admob_ad_unit.addStringProperty("Earnings");
        admob_ad_unit.addStringProperty("IndividualAdImpressions");
        admob_ad_unit.addStringProperty("IndividualAdImpressionsCtr");
        admob_ad_unit.addStringProperty("IndividualAdImpressionsRpm");
        admob_ad_unit.addStringProperty("MatchedAdRequests");
        admob_ad_unit.addStringProperty("MatchedAdRequestsCtr");
        admob_ad_unit.addStringProperty("MatchedAdRequestsRpm");
        admob_ad_unit.addStringProperty("PageViews");
        admob_ad_unit.addStringProperty("PageViewsCtr");
        admob_ad_unit.addStringProperty("PageViewsRpm");


        //Admob per day
        Entity admob_day = schema.addEntity("AdmobDay");
        admob_day.addIdProperty();
        admob_day.addStringProperty("Date");
        admob_day.addStringProperty("AdRequests");
        admob_day.addStringProperty("AdRequestsCoverage");
        admob_day.addStringProperty("Clicks");
        admob_day.addStringProperty("CostPerClick");
        admob_day.addStringProperty("Earnings");
        admob_day.addStringProperty("IndividualAdImpressions");
        admob_day.addStringProperty("IndividualAdImpressionsCtr");
        admob_day.addStringProperty("IndividualAdImpressionsRpm");
        admob_day.addStringProperty("MatchedAdRequests");
        admob_day.addStringProperty("MatchedAdRequestsCtr");
        admob_day.addStringProperty("MatchedAdRequestsRpm");
        admob_day.addStringProperty("PageViews");
        admob_day.addStringProperty("PageViewsCtr");
        admob_day.addStringProperty("PageViewsRpm");

    }
}