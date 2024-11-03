package vn.edu.usth.weather;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "Weather";
    private MediaPlayer mp;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


//        handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                // This method is executed in main thread
//                String content = msg.getData().getString("server_response");
//                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
//            }
//        };


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

    private class asyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            try {
                url = new URL("https://upload.wikimedia.org/wikipedia/commons/7/70/Example.png");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            HttpURLConnection connection =
                    null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }
            connection.setDoInput(true);
            // allow reading response code and response dataconnection.
            try {
                connection.connect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int response = 0;
            try {
                response = connection.getResponseCode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Log.i("USTHWeather", "The response is: " + response);
            InputStream is;
            try {
                is = connection.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Process image response
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            // do some preparation here, if needed
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                ImageView logo = findViewById(R.id.background_image_view); // Ensure your ForecastFragment has this ImageView
                logo.setImageBitmap(result);
            } else {
                Toast.makeText(WeatherActivity.this, "Failed to download image", Toast.LENGTH_SHORT).show();
            }
        }


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
            new asyncTask().execute();
            return true;
        } else if (id == R.id.settings) {
            // Start the new activity for settings
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//        private void networkSimulationWithThread() {
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        // Simulate long network access (5 seconds delay)
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    // Assume we got some data from the server
//                    Bundle bundle = new Bundle();
//                    bundle.putString("server_response", "Sample JSON from thread");
//
//                    // Notify the main thread via handler
//                    Message msg = new Message();
//                    msg.setData(bundle);
//                    handler.sendMessage(msg); // Send message to Handler
//                }
//            });
//            t.start();
//        }


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
