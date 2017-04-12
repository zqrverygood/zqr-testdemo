package com.zqr.snake.mytest.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zqr.snake.mytest.R;
import com.zqr.snake.mytest.model.Contributor;
import com.zqr.snake.mytest.model.Item;
import com.zqr.snake.mytest.model.MyTest;
import com.zqr.snake.mytest.model.Owner;
import com.zqr.snake.mytest.model.RetrofitBean;
import com.zqr.snake.mytest.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.text.TextUtils.isEmpty;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RetrofitActivity";
    private TextView tv_Retrofit;
    private GitHubApi mGitHubService;
    private String mUserName;
    private String mRepo;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_retrofit);
        initDate();
        tv_Retrofit = (TextView) findViewById(R.id.tv_Retrofit);
        (findViewById(R.id.tv_qingqiu)).setOnClickListener(this);
        (findViewById(R.id.tv_tongbu)).setOnClickListener(this);
        (findViewById(R.id.tv_get)).setOnClickListener(this);
        (findViewById(R.id.tv_get2)).setOnClickListener(this);
        (findViewById(R.id.tv_text11)).setOnClickListener(this);
        (findViewById(R.id.tv_rxjava)).setOnClickListener(this);
        (findViewById(R.id.tv_rxjava2)).setOnClickListener(this);
        (findViewById(R.id.tv_text12)).setOnClickListener(this);

    }
    @Override
    protected void onDestroy() {
        RxUtils.unSubscribeIfNotNull(mSubscriptions);
        super.onDestroy();
    }
    private void initDate() {
        mGitHubService = ApiClient.getInstance().getService();
        mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mUserName = getResources().getString(R.string.user_name);
        mRepo = getResources().getString(R.string.repo);
    }


    //简单实例
    private void getInfo() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        Call<List<Contributor>> call = mGitHubService.contributorsByAddConverterGetCall(mUserName, mRepo);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> contributorList = response.body();
                tv_Retrofit.setText(contributorList.get(0).getLogin());
                for (Contributor contributor : contributorList){
                    Log.e("aaaaaaa", contributor.getLogin());
                    Log.e("bbbbbbbbbbbbbbb", contributor.getContributions() + "");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //简单请求
            case R.id.tv_qingqiu:
                getInfo();
                break;
            //同步请求
            case R.id.tv_tongbu:
                getInfo2();
                break;
            //通过get请求，使用@Query
            case R.id.tv_get:
                getInfo3(null);
                break;
            //通过get请求，使用@QueryMap
            case R.id.tv_get2:
                Map<String,String> queryMap = new HashMap<>();
                queryMap.put("q", "retrofit");
                queryMap.put("since","2016-03-29");
                queryMap.put("page","1");
                queryMap.put("per_page", "3");
                getInfo3(queryMap);
                break;
            case R.id.tv_text11:
//               code=js&day=0&city=&dfc=1&charset=utf-8
                Map<String,String> querytextMap = new HashMap<>();
                querytextMap.put("code", "js");
                querytextMap.put("day","0");
                querytextMap.put("city","深圳");
                querytextMap.put("dfc", "1");
                querytextMap.put("charset", "utf-8");
                getInfoText(querytextMap);
                break;
            //rxJava+retrofit
            case R.id.tv_rxjava:
                getInfo5();
                break;
            //rxJava+retrofit
            case R.id.tv_rxjava2:
                getInfo6();
                break;
            case R.id.tv_text12:
                Map<String,String> querytextMap2 = new HashMap<>();
                querytextMap2.put("user", "aaa");
                querytextMap2.put("age", "18");
                getmyhttp(querytextMap2);
                break;
        }
    }

    private void getInfo6() {
        mSubscriptions.add(mGitHubService.contributorsByRxJava(mUserName, mRepo)
                .flatMap(new Func1<List<Contributor>, Observable<Contributor>>() {
                    @Override
                    public Observable<Contributor> call(List<Contributor> contributors) {
                        return Observable.from(contributors);
                    }
                })
                .flatMap(new Func1<Contributor, Observable<Pair<User, Contributor>>>() {
                    @Override
                    public Observable<Pair<User, Contributor>> call(Contributor contributor) {
                        Observable<User> userObservable = mGitHubService.userByRxJava(contributor.getLogin())
                                .filter(new Func1<User, Boolean>() {
                                    @Override
                                    public Boolean call(User user) {
                                        return !isEmpty(user.getName()) && !isEmpty(user.getEmail());
                                    }
                                });

                        return Observable.zip(userObservable,
                                Observable.just(contributor),
                                new Func2<User, Contributor, Pair<User, Contributor>>() {
                                    @Override
                                    public Pair<User, Contributor> call(User user, Contributor contributor) {
                                        return new Pair<>(user, contributor);
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pair<User, Contributor>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Pair<User, Contributor> pair) {
                        User user = pair.first;
                        Contributor contributor = pair.second;
                        Log.d(TAG, "name:" + user.getName());
                        Log.d(TAG, "contributions:" + contributor.getContributions());
                        Log.d(TAG, "email:" + user.getEmail());
                        tv_Retrofit.setText("rxjava + retotfit2....."+user.getName()+"..."+user.getEmail());

                    }
                }));
    }


    private void getInfo5() {
        mSubscriptions.add(
                mGitHubService.contributorsByRxJava(mUserName, mRepo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<Contributor>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(List<Contributor> contributors) {
                                for (Contributor c : contributors) {
                                    Log.e("TAG", "login:" + c.getLogin() + "  contributions:" + c.getContributions());
                                    tv_Retrofit.setText("rxjava + retotfit....."+contributors.get(1).getLogin());
                                }
                            }
                        }));
    }


    /**
     * get请求
     * @param queryMap
     */
    private void getInfo3(final Map<String,String> queryMap) {
        Call<RetrofitBean> call;
        if (queryMap == null || queryMap.size() == 0){
            call = mGitHubService.queryRetrofitByGetCall("retrofit", "2016-03-29", 1, 3);
        } else {
            call = mGitHubService.queryRetrofitByGetCallMap(queryMap);
        }

        call.enqueue(new Callback<RetrofitBean>() {
            @Override
            public void onResponse(Call<RetrofitBean> call, Response<RetrofitBean> response) {
                RetrofitBean retrofit = response.body();
                List<Item> list = retrofit.getItems();
                if (list == null)
                    return;
                Log.e(TAG, "total:" + retrofit.getTotalCount());
                Log.e(TAG, "incompleteResults:" + retrofit.getIncompleteResults());
                Log.e(TAG, "----------------------");
                for (Item item : list) {
                    Log.e(TAG, "name:" + item.getName());
                    Log.e(TAG, "full_name:" + item.getFull_name());
                    Log.e(TAG, "description:" + item.getDescription());
                    Owner owner = item.getOwner();
                    Log.e(TAG, "login:" + owner.getLogin());
                    Log.e(TAG, "type:" + owner.getType());
                    if (queryMap != null){
                        tv_Retrofit.setText(item.getName()+queryMap.toString());
                    }else {
                        tv_Retrofit.setText(item.getName());
                    }
                }

            }

            @Override
            public void onFailure(Call<RetrofitBean> call, Throwable t) {

            }
        });
    }
    /**
     * get请求
     * @param queryMap
     */
    private void getInfoText(final Map<String,String> queryMap) {
        Call<RetrofitBean> call;
        if (queryMap == null || queryMap.size() == 0){
            call = mGitHubService.queryRetrofitByGetCall("retrofit", "2016-03-29", 1, 3);
        } else {
            call = mGitHubService.sinaGetInfoText(queryMap);
        }

        call.enqueue(new Callback<RetrofitBean>() {
            @Override
            public void onResponse(Call<RetrofitBean> call, Response<RetrofitBean> response) {

                Log.e("fffffff",".."+response.toString());
                RetrofitBean retrofit = response.body();
                List<Item> list = retrofit.getItems();
                if (list == null)
                    return;
                Log.e(TAG, "total:" + retrofit.getTotalCount());
                Log.e(TAG, "incompleteResults:" + retrofit.getIncompleteResults());
                Log.e(TAG, "----------------------");
                for (Item item : list) {
                    Log.e(TAG, "name:" + item.getName());
                    Log.e(TAG, "full_name:" + item.getFull_name());
                    Log.e(TAG, "description:" + item.getDescription());
                    Owner owner = item.getOwner();
                    Log.e(TAG, "login:" + owner.getLogin());
                    Log.e(TAG, "type:" + owner.getType());
                    if (queryMap != null){
                        tv_Retrofit.setText(item.getName()+queryMap.toString());
                    }else {
                        tv_Retrofit.setText(item.getName());
                    }
                }

            }

            @Override
            public void onFailure(Call<RetrofitBean> call, Throwable t) {

            }
        });
    }


    /**
     * 同步请求
     */
    private void getInfo2() {
        final Call<List<Contributor>> call = mGitHubService.contributorsByAddConverterGetCall(mUserName, mRepo);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response<List<Contributor>> response = call.execute();

                    List<Contributor> contributorsList = response.body();
                    for (Contributor contributor : contributorsList){
                        Log.e("ccccccc",contributor.getLogin());
                        Log.e("ddddddddddd",contributor.getContributions()+"");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //请求自己写的接口
    private void getmyhttp(final Map<String,String> queryMap) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Log.e("00000","000000");
        Call<MyTest> call = mGitHubService.MyTestGetCall(queryMap);
        call.enqueue(new Callback<MyTest>() {
            @Override
            public void onResponse(Call<MyTest> call, Response<MyTest> response) {
                Log.e("1111111","1111111111");
                MyTest myTest = response.body();
                if (myTest.getCode().equals("1000")){
                    tv_Retrofit.setText(myTest.getData());
                }else {
                    Toast.makeText(RetrofitActivity.this,myTest.getData(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MyTest> call, Throwable t) {
                Log.e("22222222","222222222222222.");
            }
        });
    }

}
