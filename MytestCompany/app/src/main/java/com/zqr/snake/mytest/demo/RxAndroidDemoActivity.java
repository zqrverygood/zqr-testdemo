package com.zqr.snake.mytest.demo;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zqr.snake.mytest.R;
import com.zqr.snake.mytest.model.User;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class RxAndroidDemoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "RxAndroidDemoActivity";
    private TextView tv_show, tv_show2;
    private Subscriber<String> subscriber;//订阅者
    private Observable<String> observable;//被观察者
    private Action1<String> onNextAction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid_demo);

        findViewById(R.id.tv_ok).setOnClickListener(this);
        findViewById(R.id.tv_ok2).setOnClickListener(this);
        findViewById(R.id.tv_ok3).setOnClickListener(this);
        tv_show = (TextView) findViewById(R.id.tv_show);
        tv_show2 = (TextView) findViewById(R.id.tv_show2);

      //  createSubscriber();//创建观察订阅者
        createSubscriberByAction();//通过acton的方式创建订阅者  简化subscriber

    }
    //创建观察订阅者
    private void createSubscriber() {



         subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext");
                tv_show.setText(s);
            }
        };
        }

    //创建观察订阅者2
    private void createSubscriberByAction() {
         onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                tv_show2.setText(s);
            }
        };
    }


    private String getHello() {
        return "Hello RxAndroid  艹";
    }

    private String getHello1() {
        return "Hello RxAndroid  尼";
    }
    private String getHello2() {
        return "Hello  玛";
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok:
                createObservable();//创建被观察着
                break;
            case R.id.tv_ok2:
                createObservable2();//创建被观察者2
                break;
            case R.id.tv_ok3:
                createObservableByMap();//创建被观察者3
                break;
        }
    }

    //绑定
    private void bindSubscriber() {
        //没有action的时候
        //observable.subscribe(subscriber);

        //用action的时候
        observable.subscribe(onNextAction);
    }
    private void createObservable() {

        Log.d(TAG, "observable");
        //1.createObservable
//       observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext(getHello1());
//                subscriber.onCompleted();
//            }
//        });

        //2.createObservable可以通过just方法简化  只能执行一次
        observable = Observable.just(getHello());

        //绑定   这里创建了Observable，用来发送一字符串，
        // 然后创建了Subscriber，用来接收事件处理，然后把这两个绑定，按下按钮后，
        // subscriber会调用onNext方法和onCompleted方法
        bindSubscriber();
    }

    private void createObservable2(){
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(getHello1());
                subscriber.onCompleted();
            }
        });
        bindSubscriber();
    }


    //运行结果后原来字符串加上了了   赵日天  。
    // 其实map的功能就是在observable和subscribe之间可以对数据进行操作。
    private void createObservableByMap() {
        Log.e(TAG, "createObservableByMap");
        Observable.just(getHello2()).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " 赵日天";
            }
        }).subscribe(onNextAction);
    }

}







//1:定义一个 可以被观察的对象
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onCompleted();
//            }
//        });


//2.等效以上写法
//        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");

//3.等效以上写法
//        String[] words = {"Hello", "Hi", "Aloha"};
//        Observable observable2 = Observable.from(words);