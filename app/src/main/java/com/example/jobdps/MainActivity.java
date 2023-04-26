package com.example.jobdps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar topAppBar;
    private NavigationView navBar;
    private DrawerLayout drawerLayout;
    private Button landingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAppBar = (MaterialToolbar) findViewById(R.id.topAppBar);
        navBar = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        landingButton = (Button) findViewById(R.id.landing_button);

        setSupportActionBar(topAppBar);

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        topAppBar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        // Handle profile icon press

                        break;

                    case R.id.more:
                        // Handle more item (inside overflow menu) press

                        break;
                }
                return true;
            }
        });

        navBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerFragment(item);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openHomePage() {
        Class homeFragmentClass = HomeFragment.class;
        Fragment fragment = null;

        try {
            fragment = (Fragment) homeFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.cLayout, fragment).commit();

        navBar.getMenu().findItem(R.id.nav_item1).setChecked(true);
    }

    public void selectDrawerFragment(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.nav_item1:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_item2:
                fragmentClass = ExploreFragment.class;
                break;
            case R.id.nav_item3:
                fragmentClass = DiscussFragment.class;
                break;
            case R.id.nav_item4:
                fragmentClass = PlanFragment.class;
                break;
            case R.id.nav_item5:
                fragmentClass = ScheduleFragment.class;
                break;
            case R.id.nav_item6:
                fragmentClass = CompaniesFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.cLayout, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        drawerLayout.close();
    }
}