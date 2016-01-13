package sa.revenue.general.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sa.revenue.R;

public class TransformLayoutWithHeader extends LinearLayout {
    private Boolean isAnimationRunning = false;
    private Integer duration;

    private RelativeLayout rlHeader;
    private ProgressBar pbLoading;
    private TextView tvHeader;
    private ImageView ivCollapseExpand;
    private String headerText;

    private View rootView;
    private LinearLayout llDetails;
    private OnDetailsModeVisibleListener dListener;

    ColorDrawable[] colorFadeClick = {new ColorDrawable(getResources().getColor(R.color.primary_text)), new ColorDrawable(getResources().getColor(R.color.secondary_text))};
    TransitionDrawable fadeColorTrans = new TransitionDrawable(colorFadeClick);

    public TransformLayoutWithHeader(Context context) {
        super(context);
    }

    public TransformLayoutWithHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public interface OnDetailsModeVisibleListener {
        public void onDetailsModeVisible(View v);
    }

    public void setOnDetailsModeVisibleListener(OnDetailsModeVisibleListener dListener) {
        this.dListener = dListener;
    }

    private void init(final Context context, AttributeSet attrs) {
        rootView = View.inflate(context, R.layout.transform_layout, this);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TransformLayoutWithHeader);
        final int detailsID = typedArray.getResourceId(R.styleable.TransformLayoutWithHeader_detailsLayout, -1);

        rlHeader = (RelativeLayout) rootView.findViewById(R.id.header);
        pbLoading = (ProgressBar) rootView.findViewById(R.id.pbLoading);
        tvHeader = (TextView) rootView.findViewById(R.id.tvHeader);
        ivCollapseExpand = (ImageView) rootView.findViewById(R.id.ivCollapseExpand);
        llDetails = (LinearLayout) rootView.findViewById(R.id.details);
        if (detailsID == -1)
            throw new IllegalArgumentException("details layout required!");

        headerText = typedArray.getString(R.styleable.TransformLayoutWithHeader_headerText);
        tvHeader.setText(headerText);
        duration = typedArray.getInt(R.styleable.TransformLayoutWithHeader_t_duration, getContext().getResources().getInteger(android.R.integer.config_shortAnimTime));

        final View details = View.inflate(context, detailsID, null);
        details.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        llDetails.addView(details);
        llDetails.setVisibility(GONE);
        rlHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVisibilityOnClick();
            }
        });
    }

    public void addRevenueToHeader(String revenue) {
        tvHeader.setText(headerText + " " + revenue);
    }

    private void changeVisibilityOnClick() {
        if (!isAnimationRunning) {
            showDetails(llDetails.getVisibility() == VISIBLE, true);

            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimationRunning = false;
                }
            }, duration);
        }
    }

    private void showDetails(boolean show, boolean animate) {
        if (show) {
            collapse(llDetails);
            ivCollapseExpand.setImageDrawable(getResources().getDrawable(R.drawable.menu_down));
        } else {
            expand(llDetails);
            ivCollapseExpand.setImageDrawable(getResources().getDrawable(R.drawable.menu_up));
        }

        if (null != dListener)
            dListener.onDetailsModeVisible(rootView);
        if (animate) colorFadeClick();
    }

    private void colorFadeClick() {
        rlHeader.setBackgroundDrawable(fadeColorTrans);
        fadeColorTrans.startTransition(250);
    }


    private void expand(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = (interpolatedTime == 1) ? LayoutParams.WRAP_CONTENT : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    public LinearLayout getDetailsView() {
        return llDetails;
    }

    public View getRootView() {
        return rootView;
    }

    public void showDetails(boolean animate) {
        if (!isAnimationRunning) {
            showDetails(true, animate);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isAnimationRunning = false;
                }
            }, duration);
        }
    }

    public void setLoading(boolean loading) {
        pbLoading.setVisibility(loading ? VISIBLE : GONE);
    }

}


