package com.zqr.snake.mytest.Animator;

import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
