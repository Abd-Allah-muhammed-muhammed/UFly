package com.abdallah.ufly.ui.bottom_nav;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Color.WHITE;


public class BottomNavView extends BottomNavigationView {
    private Path path ;
    private Paint paint ;

    // the cycle of fab bottom
    public final int CYCLE_BOTTOM_RADIUS = 90;

    // first curve
    public Point mFirstCurvePoitnStart = new Point();
    public Point mFirstCurvePoitnEnd = new Point();
    public Point mFirstCurvePoitControl1 = new Point();
    public Point mFirstCurvePoitControl2 = new Point();

 // Second curve
    public Point mSecondCurvePoitnStart = new Point();
    public Point mSecondCurvePoitnEnd = new Point();
    public Point mSecondCurvePoitControl1 = new Point();
    public Point mSecondCurvePoitControl2 = new Point();

    public  int mNavigationBarWidth ,mNavigationBarHeight;

    public BottomNavView(Context context) {
        super(context);
        initView();
    }

    public BottomNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BottomNavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(WHITE);
        setBackgroundColor(TRANSPARENT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // get width ang height on nav bar
        mNavigationBarHeight = getHeight();
        mNavigationBarWidth = getWidth();

        // before curve
        mFirstCurvePoitnStart.set((mNavigationBarWidth/2)-(CYCLE_BOTTOM_RADIUS*2)-(CYCLE_BOTTOM_RADIUS/3),0);
        //after curve
        mFirstCurvePoitnEnd.set((mNavigationBarWidth/2),CYCLE_BOTTOM_RADIUS+(CYCLE_BOTTOM_RADIUS/4));

        // before curve
        mSecondCurvePoitnStart = mFirstCurvePoitnEnd;
        // after curve
        mSecondCurvePoitnEnd.set((mNavigationBarWidth/2)+(CYCLE_BOTTOM_RADIUS*2)+(CYCLE_BOTTOM_RADIUS/3),0);


        mFirstCurvePoitControl1.set(mFirstCurvePoitnEnd.x-(CYCLE_BOTTOM_RADIUS*2)+CYCLE_BOTTOM_RADIUS
        ,mFirstCurvePoitnEnd.y);


        mFirstCurvePoitControl2.set(mFirstCurvePoitnEnd.x-(CYCLE_BOTTOM_RADIUS*2)+CYCLE_BOTTOM_RADIUS,mFirstCurvePoitnEnd.y);



        mSecondCurvePoitControl1.set(mSecondCurvePoitnStart.x+(CYCLE_BOTTOM_RADIUS*2)-CYCLE_BOTTOM_RADIUS,mSecondCurvePoitnStart.y);


        mSecondCurvePoitControl2.set(mSecondCurvePoitnEnd.x-(CYCLE_BOTTOM_RADIUS)+(CYCLE_BOTTOM_RADIUS/4),mSecondCurvePoitnEnd.y);




    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(0,0);
        path.lineTo(mFirstCurvePoitnStart.x,mFirstCurvePoitnStart.y);
        path.cubicTo(mFirstCurvePoitControl1.x,mFirstCurvePoitControl1.y
        ,mFirstCurvePoitControl2.x,mFirstCurvePoitControl2.y,mFirstCurvePoitnEnd.x
        ,mFirstCurvePoitnEnd.y);

 path.cubicTo(mSecondCurvePoitControl1.x,mSecondCurvePoitControl1.y
        ,mSecondCurvePoitControl2.x,mSecondCurvePoitControl2.y,mSecondCurvePoitnEnd.x
        ,mSecondCurvePoitnEnd.y);

 path.lineTo(mNavigationBarWidth,0);
 path.lineTo(mNavigationBarWidth,mNavigationBarHeight);
 path.lineTo(0,mNavigationBarHeight);
 path.close();
 canvas.drawPath(path,paint);



    }
}
