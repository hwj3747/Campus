package com.haiyangroup.scampus.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.haiyangroup.scampus.data.AbsReturn;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.RetrofitError;
import rx.Observer;
/**
 * 公共观察者，用来过滤一些公共错误
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
public abstract class EndObserver<T> implements Observer<T> {
    public abstract void onEnd();
    public abstract void onMyNext(T entity);

    static Context mContext;
    public static void init(Context context){
        mContext=context;
    }

    @Override
    public void onCompleted() {
        onEnd();
    }

    @Override
    public void onError(Throwable e) {

        Throwable throwable = e;
        //获取最根源的异常
        while(throwable.getCause() != null){
            e = throwable;
            throwable = throwable.getCause();
        }

        Log.i("exception",e.toString());
        if (e instanceof ConnectException){             //HTTP错误
            Toast.makeText(mContext,"网络异常！",Toast.LENGTH_SHORT).show();
        }
        if (e instanceof RetrofitError){             //HTTP错误
            Toast.makeText(mContext,"连接失败",Toast.LENGTH_SHORT).show();
        }
//
//        Throwable e1=new Throwable(new ConnectException());
//        Log.i("error", e1.toString());
//
//        onEnd();
//        if(e instanceof ConnectException)
//            Log.i("error", e.toString());
//       // onEnd();
//        if(e.toString().indexOf("failed to connect to")!=-1)
//            Toast.makeText(mContext,"网络异常,请检查网络设置",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T entity) {
        if(entity instanceof AbsReturn) {
            if (((AbsReturn) entity).getCode() == 101) {
                Toast.makeText(mContext, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
            }
        }
        onMyNext(entity);
    }
}
