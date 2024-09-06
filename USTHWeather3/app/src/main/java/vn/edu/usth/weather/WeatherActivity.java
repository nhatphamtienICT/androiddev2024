package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;



public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "Weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        Log.i(TAG, "Create");

        //delete at practical 6
//        ForecastFragment firstFragment = new ForecastFragment();
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.main, firstFragment).commit();
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
        Log.i(TAG, "Destroy");
    }
}
