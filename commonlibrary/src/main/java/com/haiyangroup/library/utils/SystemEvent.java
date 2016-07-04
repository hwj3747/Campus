package com.haiyangroup.library.utils;

import android.os.Message;

import com.haiyangroup.library.log.LogUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的全局事件,观察者模式
 * @author 郑晓彬
 */
public class SystemEvent {

	private static final String TAG = "SystemEvent";

	private static Map<Integer, ArrayList<WeakReference<IEventListener>>> mEventMap = new HashMap<Integer, ArrayList<WeakReference<IEventListener>>>();
	private static Map<Integer, ArrayList<WeakReference<IEventListener>>> mTmpEventMap = new HashMap<Integer, ArrayList<WeakReference<IEventListener>>>();

	/**
	 * 自定义的事件监听器
	 */
	public interface IEventListener {
		void onEvent(Message msg);
	}

	/**
	 * 暂存监听器
	 */
	public static void storeListener() {
		mTmpEventMap.putAll(mEventMap);
		mEventMap.clear();
	}

	/**
	 * 还原监听器
	 */
	public static void restoreListener() {
		mEventMap.putAll(mTmpEventMap);
		mTmpEventMap.clear();
	}

	/**
	 * 加入监听器
	 * 
	 * @param listener
	 */
	public static void addListener(int eventType, IEventListener listener) {
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(eventType);
		if (null == list)
			list = new ArrayList<WeakReference<IEventListener>>();
		WeakReference<IEventListener> wrf = new WeakReference<IEventListener>(
				listener);
		// 如果已经存在同一个监听者，就不添加
		for (WeakReference<IEventListener> weakReference : list) {
			IEventListener temp=weakReference.get();
			if (temp!=null&&(temp).hashCode() == (listener).hashCode()) {
				return;
			}
			 
		}
		if (list.contains(wrf)) {
			return;
		}
		list.add(wrf);
		mEventMap.put(eventType, list);

	}

	/**
	 * 移除监听器
	 * 
	 * @param listener
	 */
	public static void removeListener(int eventType, IEventListener listener) {
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(eventType);
		if (null == list)
			return;
		for (int i = 0; i < list.size(); i++) {
			IEventListener l = list.get(i).get();
			if (l == listener) {
				list.remove(i);
				break;
			}
		}
	}

	/**
	 * 移除当前Event的所有Listener
	 * 
	 * @param eventType
	 */
	public static void removeListener(int eventType) {
		LogUtils.d(TAG, "removeListener = " + eventType);
		mEventMap.remove(eventType);
	}

	public static void removeListenerAll() {
//		mEventMap.clear();
        Map<Integer, ArrayList<WeakReference<IEventListener>>> tempMap = new HashMap<Integer, ArrayList<WeakReference<IEventListener>>>();
        if (null != mEventMap.get(1000)) {
            tempMap.put(1000, mEventMap.get(1000));
        }
        mEventMap.clear();
        if (tempMap.size() > 0) {
            mEventMap.put(1000, tempMap.get(1000));
        }
    }

    /**
	 * 激活监听器
	 * 
	 * @param msg
	 */
	public static void fireEvent(Message msg) {
//		long time3 = System.currentTimeMillis();
		ArrayList<WeakReference<IEventListener>> list = mEventMap
				.get(msg.what);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				IEventListener listener = list.get(i).get();
				if (listener != null) {
					// long time1 = System.currentTimeMillis();
					listener.onEvent(msg);
				}
			}
		}

	}

	// /**
	// * 响应监听器
	// * @param eventType
	// */
	// public static void fireEvent(int eventType) {
	// fireEvent(eventType);
	// }

}
