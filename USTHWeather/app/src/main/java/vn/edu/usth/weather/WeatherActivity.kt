package vn.edu.usth.weather

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity


class WeatherActivity : AppCompatActivity(){
    private val key = "WeatherActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(key,"Create")
        val firstFragment = ForecastFragment()
        supportFragmentManager.beginTransaction().add(R.id.main, firstFragment).commit();
    }


    override fun onStart(){
        super.onStart()
        Log.i(key,"Start")
    }
    override fun onResume(){
        super.onResume()
        Log.i(key,"Resume")
    }
    override fun onPause(){
        super.onPause()
        Log.i(key,"Pause")
    }
    override fun onStop(){
        super.onStop()
        Log.i(key,"Sop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i(key,"Destroy")
    }

}
