/*
 * D.java
 * classes : com.cloud.app.isDebug.D
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年7月7日 下午2:24:10
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.zqr.snake.mytest.util;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * cn.icnt.dinners.isDebug.isDebugUtil
 * 
 * @author Andrew Lee <br/>
 *         create at 2014年7月7日 下午2:24:10
 */
public class MyLog {
//	private static final boolean isDebug = false;
//	private static final boolean isDebug = true;

	private static Boolean isDebug = null;

	public static boolean isisDebug() {
		return isDebug == null ? false : isDebug.booleanValue();
	}

	/**
	 * Sync lib isDebug with app's isDebug value. Should be called in module Application
	 *
	 * @param context
	 */
	public static void syncIsDebug(Context context) {
		if (isDebug == null) {
			isDebug = context.getApplicationInfo() != null &&
					(context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		}
	}

	public static void toast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void i(String msg) {
		if (isDebug) {
			Log.i("sys", msg);
		}
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}
}