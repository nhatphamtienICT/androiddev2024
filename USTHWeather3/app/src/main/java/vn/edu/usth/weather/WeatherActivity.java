package vn.edu.usth.weather;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
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
        mp = MediaPlayer.create(this,R.raw.wam_music);
        mp.start();

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);

        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        new TabLayoutMediator(tabLayout,viewPager, (tab, position) -> tab.setText(adapter.getPageTitle(position))).attach();

        Log.i(TAG, "Create");

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(TAG, "Start");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG, "Resume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(TAG, "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Stop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mp != null){
            if(mp.isPlaying()){
                mp.stop();
            }
            mp.release();
            mp = null;
        }
        Log.i(TAG, "Destroy");
    }
}
