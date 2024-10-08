package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

         handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
            // This method is executed in main thread
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };



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
            // Call function to run network simulation
            networkSimulationWithThread();
            return true;
        } else if (id == R.id.settings) {
            // Start the new activity for settings
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        private void networkSimulationWithThread() {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Simulate long network access (5 seconds delay)
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Assume we got some data from the server
                    Bundle bundle = new Bundle();
                    bundle.putString("server_response", "Sample JSON from thread");

                    // Notify the main thread via handler
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg); // Send message to Handler
                }
            });
            t.start();
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
