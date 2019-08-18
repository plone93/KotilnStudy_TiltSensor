package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint : Paint = Paint()
    private val blackPaint : Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f
    
    //센서값 받기
    fun onSensorEvent(event: SensorEvent) {
        //화면을 가로로 돌렸으므로 x축과 y축을 서로 바꿈
        xCoord = event.values[0] * 20
        yCoord = event.values[1] * 20
        
        invalidate() //뷰의 onDraw() 메서드를 다시 호출하는 메서드, 즉 뷰를 다시 그림
    }

    init {
        //녹색 페인트
        greenPaint.color = Color.GREEN  // Color클래스에 선언된 색상들을 지정
        
        //검정색 테두리 페인트
        blackPaint.style = Paint.Style.STROKE
        /*
        * style 프로퍼티는 다음 속성이 있음
        * FILL : 색을 채웁니다
        * FILL_AND_STROKE : 획과 관련된 설정을 유지하면서 색을 채웁니다
        * STROKE : 획관련 설정을 유지하여 왼곽선만 그립니다
        * */
    }

    // 좌표 계산
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cX = w/2f
        cY = h/2f
    }

    //그리기
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(cX, cY, 100F, blackPaint) //바깥 원
        canvas?.drawCircle(cX, cY, 100F, greenPaint) //녹색 원

        //가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)


    }
}