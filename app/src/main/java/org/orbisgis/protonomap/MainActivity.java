package org.orbisgis.protonomap;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    // For the list view
    public ListView mDrawerList;
    public ArrayAdapter<String> mAdapter;
    public DrawerLayout mDrawerLayout;
    public String[] mMenuLeft;
    public ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    // For the menu option
    public static String pagetosee;
    public static String titletosee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

    }

    // Drawer navigation
    void initDrawer() {
        try {
            // List view
            mMenuLeft = getResources().getStringArray(R.array.dm_list_array);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);
            // Set the adapter for the list view
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_item, mMenuLeft));
            // Set the list's click listener
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
            // Display the List view into the action bar
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,                  /* host Activity */
                    mDrawerLayout,         /* DrawerLayout object */
                    R.string.drawer_open,  /* "open drawer" description */
                    R.string.drawer_close  /* "close drawer" description */
            ) {
                /**
                 * Called when a drawer has settled in a completely closed state.
                 */
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getSupportActionBar().setTitle(getTitle());
                }

                /**
                 * Called when a drawer has settled in a completely open state.
                 */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle(getString(R.string.title_menu));
                }
            };
            // Set the drawer toggle as the DrawerListener
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            e.printStackTrace();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch(position) {
                case 0:
                    Intent im = new Intent(getApplicationContext(),Measurement.class);
                    pagetosee=getString(R.string.url_help);
                    titletosee=getString((R.string.title_activity_help));
                    //mDrawerLayout.closeDrawers();
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(im);
                    break;
                case 1:
                    //Toast.makeText(getApplicationContext(), "Some Activities MAY take time to load. Please be Patient.", Toast.LENGTH_LONG).show();
                    //Intent a = new Intent(MainActivity.this, Dev_team.class);
                    //startActivity(a);
                    // mDrawerLayout.closeDrawer(view);
                    break;
                case 2:
                    Intent ih = new Intent(getApplicationContext(),View_html_page.class);
                    pagetosee=getString(R.string.url_help);
                    titletosee=getString((R.string.title_activity_help));
                    //mDrawerLayout.closeDrawers();
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(ih);
                    break;
                case 3:
                    Intent ia = new Intent(getApplicationContext(),View_html_page.class);
                    pagetosee=getString(R.string.url_about);
                    titletosee=getString((R.string.title_activity_about));
                    mDrawerLayout.closeDrawer(mDrawerList);
                    startActivity(ia);
                default:
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mDrawerLayout.closeDrawers();

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent is = new Intent(getApplicationContext(),Settings.class);
                startActivity(is);
            return true;
            /*
            case R.id.action_about:
                Intent ia = new Intent(getApplicationContext(),View_html_page.class);
                pagetosee=getString(R.string.url_about);
                titletosee=getString((R.string.title_activity_about));
                startActivity(ia);
                return true;
                */
            default:
                return super.onOptionsItemSelected(item);
        }

    }

     // Color for noise exposition representation
    public static final int[] NE_COLORS = {
            Color.rgb(255, 0, 0), Color.rgb(255, 128, 0), Color.rgb(255, 255, 0), Color.rgb(128, 255, 0), Color.rgb(0, 255, 0)
    };
    // Choose color category in function of sound level
    public int getNEcatColors(float SL) {

        int NbNEcat;

        if(SL > 75.)
        {
            NbNEcat=0;
        }
        else if( (SL<=75) & (SL>65))
        {
            NbNEcat=1;
        }
        else if( (SL<=65) & (SL>55))
        {
            NbNEcat=2;
        }
        else if( (SL<=55) & (SL>45))
        {
            NbNEcat=3;
        }
        else
        {
            NbNEcat=4;
        }
        return NbNEcat;
    }

    // Color for spectrum representation (min, iSL, max)
    public static final int[] SPECTRUM_COLORS = {
            Color.rgb(0, 128, 255), Color.rgb(102, 178, 255), Color.rgb(204, 229, 255),
    };

    public int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int []colors = new int[stacksize];

        for(int i = 0; i < stacksize; i++) {
            colors[i] = SPECTRUM_COLORS[i];
        }

        return colors;
    }

}