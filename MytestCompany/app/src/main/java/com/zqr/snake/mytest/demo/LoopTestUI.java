package com.zqr.snake.mytest.demo;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;



/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class LoopTestUI {
    public static void start(){
        Looper.getMainLooper().setMessageLogging(new Printer() {
            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";
            @Override
            public void println(String x) {
                if (x.startsWith(START)){
                    LogMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)){
                    LogMonitor.getInstance().startMonitor();
                }
            }
        });
    }

     public static class LogMonitor{
        private static LogMonitor sInstance = new LogMonitor();
         private HandlerThread mLogThread = new HandlerThread("log");
         private Handler mIoHandler;
         private static final long TIME_BLOCK = 1000L;

         private LogMonitor(){
             mLogThread.start();
             mIoHandler = new Handler(mLogThread.getLooper());
         }
         private static Runnable mLogRunnable = new Runnable() {
             @Override
             public void run() {
                 StringBuilder sb = new StringBuilder();
                 StackTraceElement[] stackTrace =
                         Looper.getMainLooper().getThread().getStackTrace();
                 for (StackTraceElement s : stackTrace){
                     sb.append(s.toString()+"\n");
                 }
                 Log.e("LoopTEstUI",sb.toString());
             }
         };
         public static LogMonitor getInstance(){
             return sInstance;
         }
         public boolean isMonitor(){
//             return mIoHandler.hasCallbacks(mLogRunnable);
             return true;
         }

         public void startMonitor(){
             mIoHandler.postDelayed(mLogRunnable,TIME_BLOCK);
         }
         public void removeMomitor(){
             mIoHandler.removeCallbacks(mLogRunnable);
         }

    }

}
