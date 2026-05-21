package ensa.ma.sensors;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.hardware.Sensor;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import ensa.ma.sensors.fragments.ActivityRecognitionFragment;
import ensa.ma.sensors.fragments.CompassFragment;
import ensa.ma.sensors.fragments.MotionSensorFragment;
import ensa.ma.sensors.fragments.SensorGraphFragment;
import ensa.ma.sensors.fragments.StepCounterFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        fragmentContainer = findViewById(R.id.fragment_container);

        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_share, R.id.nav_list, R.id.nav_temp, R.id.nav_humd, R.id.nav_compass)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_temp) {
                showSensorFragment(SensorGraphFragment.newInstance(
                        Sensor.TYPE_AMBIENT_TEMPERATURE,
                        "Température ambiante", "FIRST_VALUE"));

            } else if (id == R.id.nav_humd) {
                showSensorFragment(SensorGraphFragment.newInstance(
                        Sensor.TYPE_RELATIVE_HUMIDITY,
                        "Humidité relative", "FIRST_VALUE"));

            } else if (id == R.id.nav_proximity) {
                showSensorFragment(SensorGraphFragment.newInstance(
                        Sensor.TYPE_PROXIMITY,
                        "Capteur de proximité", "FIRST_VALUE"));

            } else if (id == R.id.nav_magnetic) {
                showSensorFragment(SensorGraphFragment.newInstance(
                        Sensor.TYPE_MAGNETIC_FIELD,
                        "Champ magnétique", "MAGNITUDE"));

            } else if (id == R.id.menu_accelerometer) {
                showSensorFragment(MotionSensorFragment.newInstance(
                        Sensor.TYPE_ACCELEROMETER,
                        "Accéléromètre : x, y, z"));

            } else if (id == R.id.menu_gravity) {
                showSensorFragment(MotionSensorFragment.newInstance(
                        Sensor.TYPE_GRAVITY,
                        "Gravité : x, y, z"));

            } else if (id == R.id.menu_gyroscope) {
                showSensorFragment(MotionSensorFragment.newInstance(
                        Sensor.TYPE_GYROSCOPE,
                        "Gyroscope : rad/s"));

            } else if (id == R.id.menu_steps) {
                showSensorFragment(new StepCounterFragment());

            } else if (id == R.id.menu_compass) {
                showSensorFragment(new CompassFragment());

            } else if (id == R.id.menu_activity) {
                showSensorFragment(new ActivityRecognitionFragment());

            } else {
                // Home, List, Share → NavController reprend la main
                fragmentContainer.setVisibility(View.GONE);
                NavigationUI.onNavDestinationSelected(item,
                        Navigation.findNavController(this, R.id.nav_host_fragment));
            }

            drawer.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Affiche un fragment capteur par-dessus le NavHost
    private void showSensorFragment(Fragment fragment) {
        fragmentContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}