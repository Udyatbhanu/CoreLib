package ubbs.home.com.corelib.sample.activity;

import android.os.Bundle;

import ubbs.home.com.core.lib.ui.activity.UBBSSlidingMenuBaseActivity;
import ubbs.home.com.corelib.sample.R;

/**
 * Created by udyatbhanu-mac on 7/3/15.
 */
public class SlidingMenuActivity extends UBBSSlidingMenuBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setUpMenu(R.raw.menu_config,savedInstanceState);
    }

//    protected void onActivityResult (int requestCode, int resultCode, Intent data){
//        int recode = requestCode;
//        int resultCode1 = resultCode;
//        Intent iData = data;
//        Log.i("test1","test");
//
//        Toast toast = Toast.makeText(this, "Picture taken: " , Toast.LENGTH_LONG);
//        toast.show();
//    }
}
