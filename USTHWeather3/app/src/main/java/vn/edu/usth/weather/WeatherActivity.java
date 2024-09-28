package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "Weather";
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mp = MediaPlayer.create(this, R.raw.wam_music);
        mp.start();

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);

        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(adapter.getPageTitle(position))).attach();

        Log.i(TAG, "Create");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "Tomorrow", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.settings) {
            // Start the new activity for settings
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
        Log.i(TAG, "Destroy");
    }
}
