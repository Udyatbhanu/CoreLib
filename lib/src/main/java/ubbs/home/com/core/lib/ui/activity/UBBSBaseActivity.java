package ubbs.home.com.core.lib.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import ubbs.home.com.core.lib.R;

/**
 * Created by udyatbhanu-mac on 7/11/15.
 * Base Activtity which provides common functionalities like access to camera api with simple utility methods
 */
public abstract class UBBSBaseActivity extends AppCompatActivity {
    ProgressBar spinner = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     *
     */
    protected void showSpinner(RelativeLayout parentView){
        spinner = (ProgressBar)getLayoutInflater().inflate(R.layout.wait_spinner, null);
        parentView.addView(spinner);
        spinner.setVisibility(View.VISIBLE);

    }


    /**
     *
     */
    protected void hideSpinner() {
        if (null != spinner) {
            if (spinner.getVisibility() == View.VISIBLE) {
                spinner.setVisibility(View.GONE);
            }

        }
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        this.overridePendingTransition(R.animator.slide_in_left,
                R.animator.slide_out_left);

//        this.overridePendingTransition(R.animator.slide_in_right,
//                R.animator.slide_out_right);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }





}
