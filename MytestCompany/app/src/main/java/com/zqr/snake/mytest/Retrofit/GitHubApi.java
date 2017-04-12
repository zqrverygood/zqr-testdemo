package com.zqr.snake.mytest.Retrofit;

import com.zqr.snake.mytest.model.Contributor;
import com.zqr.snake.mytest.model.MyTest;
import com.zqr.snake.mytest.model.RetrofitBean;
import com.zqr.snake.mytest.model.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public interface GitHubApi {

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:zqr"
    })

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:zqr"
    })
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsByAddConverterGetCall(@Path("owner") String owner, @Path("repo") String repo);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsAndAddHeader(@Path("owner") String owner, @Path("repo") String repo);

    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCall(@Query("q")String owner,
                                              @Query("since")String time,
                                              @Query("page")int page,
                                              @Query("per_page")int per_Page);

    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCallMap(@QueryMap Map<String,String> map);

    //新浪天气接口测试
    @GET("iframe/index/w_cl.php")
    Call<RetrofitBean> sinaGetInfoText (@QueryMap Map<String, String> map);

//  @GET("http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&city=&dfc=1&charset=utf-8")

    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributorsByRxJava(@Path("owner") String owner,
                                                       @Path("repo") String repo);

    @GET("users/{user}")
    Observable<User> userByRxJava(@Path("user") String user);

    @GET("PhpProject1/httptest.php")
    Call<MyTest> MyTestGetCall(@QueryMap Map<String,String> map);

}
