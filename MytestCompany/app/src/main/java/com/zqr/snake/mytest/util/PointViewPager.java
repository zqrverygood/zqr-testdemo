package com.zqr.snake.mytest.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2015/12/2.
 */
public class PointViewPager extends ViewPager {
    Context context;
    Paint paint;


    public PointViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawCycle(canvas);
    }


    //画滑动指示的小圆点
    private void drawCycle(Canvas canvas) {
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        int count = 0;
        if (this.getAdapter() != null) {
            count = this.getAdapter().getCount();
        }
        int select = getCurrentItem();
        float density = getContext().getResources().getDisplayMetrics().density;
        int itemWidth = (int) (11 * density);
        int itemHeight = itemWidth / 2;
        int x = (getWidth() - count * itemWidth)/2;
        int y = getHeight() - itemWidth;
        int minItemHeight = (int) ((float) itemHeight * 0.8F);
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            if (select == i) {
                paint.setColor(0xFFbdbdbd);
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            } else {
                paint.setColor(0xFFe6e6e6);
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            }
        }
        canvas.restore();
    }

}


