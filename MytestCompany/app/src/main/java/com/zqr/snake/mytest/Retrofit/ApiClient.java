package com.zqr.snake.mytest.Retrofit;


import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
//import retrofit2.GsonConverterFactory;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


public class ApiClient {

    private static ApiClient client;
    private static final String API_SERVER_PRODUCTION = "http://101.200.175.118:81";
    private static final String API_SERVER_DEVELOPMENT_1 = "http://192.168.0.101:8000";
    private static final String API_SERVER_DEVELOPMENT_2 = "http://192.168.0.99:8000";
    public static final String THUMBOR_SERVER = "http://101.200.175.118:7000";
    //public static final String THUMBOR_SERVER = "http://192.168.0.159:7000";
    public static final String THUMBOR_SERVER_KEY = "toporallthanks@*4587295@!jx";
    public static final String API_SERVER = API_SERVER_PRODUCTION;


    public static synchronized ApiClient getInstance() {
        if (client == null)
            client = new ApiClient();
        return client;
    }

    private GitHubApi apiService;

    private ApiClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpLoggingInterceptor.ad
        OkHttpClient   mOkHttpClient = new OkHttpClient.Builder()
//                .cookieJar(new CookiesManager())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("User-Agent", "RetrofitBean-Sample-App")
                                .addHeader("name---", "zqr")
                                .build();
                        return chain.proceed(request);
                    }
                })
                //.addNetworkInterceptor(new CookiesInterceptor(MyApplication.getInstance().getApplicationContext()))
                //设置请求读写的超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
//                .cache(cache)
                .build();
//http://lytest.lybanks.com/api/
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
                .baseUrl("http://192.168.56.1/")//自己写的接口
              //  .baseUrl("http://php.weather.sina.com.cn/")// //新浪天气接口测试
                //添加转化库，默认是Gson
                .addConverterFactory(GsonConverterFactory.create())
                //添加回调库，采用RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint(API_SERVER)
//                .setConverter(new GsonConverter(gson))
//                .setRequestInterceptor(requestInterceptor)
//                .setErrorHandler(errorHandler)
//                .setLogLevel(LogLevel.FULL)
//                .setClient(new OkClient(okHttpClient))
//                .build();
        apiService = retrofit.create(GitHubApi.class);
    }

    public GitHubApi getService() {
        return apiService;
    }

//    private RequestInterceptor requestInterceptor = new RequestInterceptor() {
//
//        @Override
//        public void intercept(RequestFacade rf) {
//            String[] strs = getToken();
//            rf.addQueryParam("token", strs[0]);
//            rf.addQueryParam("nonce", strs[1]);
//
//            if (LoginProvider.getInstance().isLogin())  {
//                rf.addHeader("AUTH_ACCESS_TOKEN", LoginProvider.getInstance().getUser().getAccessToken());
//                rf.addQueryParam("user_id", LoginProvider.getInstance().getUser().getId() + "");
//            }
//        }
//    };

//    private String[] getToken() {
//        String k = getK();
//        Calendar calendar = Calendar.getInstance();
//        String nonce = calendar.getTimeInMillis() + "";
//        return new String[]{MD5Utils.MD5(nonce + k), nonce};
//    }

    private String getK() {
        return "5k4&x1@;4xpj455dfsbfq_xxxx-z_9mbtuku*9r(p=-n(";
    }

    private ErrorHandler errorHandler = new ErrorHandler() {

        @Override
        public void warning(SAXParseException exception) throws SAXException {

        }

        @Override
        public void error(SAXParseException exception) throws SAXException {

        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {

        }


    };

}
