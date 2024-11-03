package vn.edu.usth.weather;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class PrefActivity extends AppCompatActivity {

    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            // Launch MainActivity when the back button is clicked
            Intent intent = new Intent(PrefActivity.this, WeatherActivity.class);
            startActivity(intent);
            // Optionally finish the current activity to remove it from the back stack
            finish();
        });

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        time = findViewById(R.id.url);


        Button downloadButton = findViewById(R.id.download_button);
        downloadButton.setOnClickListener(view -> {
            asyncTask task = new asyncTask();
            String sleeptime = time.getText().toString();
            task.execute(sleeptime);

        });
    }
    @SuppressLint("StaticFieldLeak")
    private class asyncTask extends AsyncTask<String,String, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            // do some preparation here, if needed
            progressDialog = ProgressDialog.show(PrefActivity.this,"Hacking ur laptop",
                    "Wait for hacking process to complete: "+time.getText().toString()+" years remaining");
        }

        @Override
        protected String doInBackground(String... params) {
            String resp;
            try {
                int time = Integer.parseInt(params[0])*1000;
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " years \nHacking Completed";
            } catch (Exception e){
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // This method is called in the main thread, so it's possible
            // to update UI to reflect the worker thread progress here.
            // In a network access task, this should update a progress bar
            // to reflect how many percent of data has been retrieved
        }

        @Override
        protected void onPostExecute(String result) {
            // This method is called in the main thread. After #doInBackground returns
            // the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()
            progressDialog.dismiss();
            Toast.makeText(PrefActivity.this, result, Toast.LENGTH_SHORT).show();

        }


    }

}
