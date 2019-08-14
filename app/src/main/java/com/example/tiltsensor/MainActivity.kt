package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    //센서값 읽기, SensorEventListener 오버라이딩
    override fun onSensorChanged(event: SensorEvent?) {
        //센서값이 변경되면 호출됨
        //value[0] : x축 값 : 위로기울이면 -10~0, 아래로 기울이면 0~10
        //value[1] : y축 값 : 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
        //value[2] : z축 값 : 미사용
        
        //디버그용 로그를 표시
        //Log.e : 에러를 표시
        //Log.w : 경고를 표시
        //Log.i : 정보성 로그를 표시
        //Log.v : 모든 로그를 표시
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x :" + "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //화면이 꺼지지 않게 하기 
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        //화면이 가로모드로 고정되게 하기
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    //센서등록
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,//센서매니저.사용할센서(센서값을 받을 대상)
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//센서종류지정
            SensorManager.SENSOR_DELAY_NORMAL)//얼마나 자주 받을지, fastest, game normal, ui ,자주 읽으면 배터리 낭비
    }
    
    //센서해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this) //센서해제
    }



}
