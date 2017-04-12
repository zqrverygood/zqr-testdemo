package com.zqr.snake.mytest.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class LinkTestDemo {
    Context mcontext;
    String mString;

    public LinkTestDemo(Context context, String s) {
       this.mcontext = context;
        this.mString = s;
    }

    public static LinkTestDemo makeLog(Context context,String s){
        return new LinkTestDemo(context, s);
    }

   public void show (){
       Toast.makeText(mcontext,mString,Toast.LENGTH_LONG).show();
   }
}
