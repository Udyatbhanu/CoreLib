package ubbs.home.com.core.lib.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ubbs.home.com.core.lib.R;
import ubbs.home.com.core.lib.ui.adapter.NavDrawerListAdapter;
import ubbs.home.com.core.lib.ui.data.NavDrawerItem;



/**
 * Created by udyatbhanu-mac on 7/3/15.
 */
public abstract class UBBSSlidingMenuBaseActivity extends UBBSBaseActivity {


    private static final String TAG = UBBSSlidingMenuBaseActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private List<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    // nav drawer title
    private CharSequence mDrawerTitle;
    String[] fragmentsArray;
    // used to store app title
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_base_ubbs);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mTitle = mDrawerTitle = getTitle();

        //Set transparency
        mDrawerList.getBackground().setAlpha(200);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,
                mDrawerLayout,

                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name
        ) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };






    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case 0:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }









    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {

          String[] fragments = null;
        public SlideMenuClickListener(String []fragmentName){
            fragments = fragmentName;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            displayView(position,fragments);


        }
    }



    private void displayView(int position, String[] fragments) {

//        if(restoreStateOnOrientationChange){
//            String fragMentTag = (String)ApplicationSession.getSessionParam(ApplicationConstants.fragmentTag);
//            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragMentTag);
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment, fragMentTag).commit();
//        }
//
//    else{
//            Fragment fragment = Fragment.instantiate(this,fragments[position]);
//            FragmentManager fragmentManager =  getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment, fragments[position]).commit();
//        }




        Fragment fragment = Fragment.instantiate(this,fragments[position]);
        FragmentManager fragmentManager =  getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, fragments[position]).commit();
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
//        ApplicationSession.setSessionParam(ApplicationConstants.fragmentTag, fragments[position] );
        mDrawerLayout.closeDrawer(mDrawerList);





    }


    /**
     *
     */
    protected void restoreState(){

        displayView(mDrawerList.getSelectedItemPosition(),fragmentsArray);

    }



    /* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     *
     * @param menuItemsConfigResource
     */
    protected void setUpMenu(int menuItemsConfigResource, Bundle savedInstanceState){

        InputStream inputStream = getResources().openRawResource(menuItemsConfigResource);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();

        navDrawerItems = new ArrayList<NavDrawerItem>();
        try {
            while (( line = bufferedReader.readLine()) != null) {
                text.append(line);

            }
            JSONObject jObject = new JSONObject(text.toString());
            JSONObject jObjectResult = jObject.getJSONObject("MenuItems");
            fragmentsArray = new String[jObjectResult.length()];
            for(int i=0; i<jObjectResult.length();i++){
                JSONObject jItem = jObjectResult.getJSONObject("option"+(i+1));

                String title = jItem.getString("title");
                String frgament = jItem.getString("fragment");

                navDrawerItems.add(new NavDrawerItem(title));
                fragmentsArray[i]=frgament;
            }
            adapter = new NavDrawerListAdapter(getApplicationContext(),
                    navDrawerItems);
            mDrawerList.setAdapter(adapter);
            mDrawerList.setOnItemClickListener(new SlideMenuClickListener(fragmentsArray));
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            // display the home fragment i.e. the first fragment in the array

            if(savedInstanceState==null){
                displayView(0,fragmentsArray);
            }

            Log.i(TAG,text.toString());
        }catch (JSONException ex) {
            Log.e(TAG,"Exception");
        }

        catch (IOException e) {
            Log.e(TAG,"Exception");
        }


    }




}
