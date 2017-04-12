package com.zqr.snake.mytest.Retrofit;


import com.zqr.snake.mytest.model.Contributor;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


//没有用到的类
public interface IApiService {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsByAddConverterGetCall(@Path("owner") String owner, @Path("repo") String repo);


//    @GET("/api/articles/")
//    public void getArticles(@Query("page") int page,
//                            @Query("page_size") int pageSize,
//                            @QueryMap Map<String, String> params, Callback<ApiListResponse<User>> cb);

//
//    @GET("/api/article/{id}")
//    public void getArticle(@Path("id") int id,
//                           Callback<Article> cb);
//
//    @GET("/api/comments/")
//    public void getComments(@Query("page") int page,
//                            @Query("page_size") int pageSize,
//                            @Query("article") int articleId,
//                            @Query("need_article_detail") int isNeedArticleDetail,
//                            @QueryMap Map<String, String> params,
//                            Callback<ApiListResponse<Comment>> cb);
//
//    @GET("/api/comments/")
//    public void getComments(@Query("page") int page,
//                            @Query("page_size") int pageSize,
//                            @Query("user") int userId,
//                            @QueryMap Map<String, String> params,
//                            Callback<ApiCommentListResponse> cb);
//
//    @POST("/api/comments/create/")
//    @FormUrlEncoded
//    public void createComment(@Field("article_id") int articleId,
//                              @Field("content") String content,
//                              Callback<ApiPostResponse<Comment>> cb);
//
//    @POST("/api/account/social/")
//    @FormUrlEncoded
//    void login(@Field("open_id") String openId,
//               @Field("open_type") int openType,
//               @Field("login_client") int loginClient,
//               @Field("profile_image") String profileImage,
//               @Field("nick") String nick,
//               Callback<ApiPostResponse<User>> cb
//    );
//
//    @POST("/api/feedback/")
//    @FormUrlEncoded
//    void feedback(@Field("content") String content,
//                  @Field("phone") String phone,
//                  @Field("email") String email,
//                  Callback<ApiPostResponse> cb);
//
//    @GET("/api/tags/")
//    void getTags(
//            Callback<List<Tag>> cb);
//
//    @GET("/api/favs/")
//    void getFavs(@Query("page") int page,
//                 @Query("page_size") int pageSize, Callback<ApiListResponse<Fav>> cb);
//
//    @POST("/api/fav/create/")
//    @FormUrlEncoded
//    void fav(@Field("article_id") int articleId,
//             Callback<ApiPostResponse<Void>> cb);
//
//    @POST("/api/fav/delete/")
//    @FormUrlEncoded
//    void disFav(@Field("article_id") int articleId,
//                Callback<ApiPostResponse<Void>> cb);
//
//    @POST("/api/clicks/create/")
//    @FormUrlEncoded
//    void clickArticle(@Field("article_id") int articleId,
//                      Callback<ApiPostResponse<Void>> cb);
//
//    @GET("/api/clicks/count/")
//    void getClicksCount(Callback<Integer> cb);
//
//    @GET("/api/clicks/")
//    void getClicks(@Query("page") int page,
//                   @Query("page_size") int pageSize,
//                   Callback<ApiListResponse<ReadHistory>> cb);
//
//    @GET("/api/topics/")
//    void getTopics(@Query("page") int page,
//                   @Query("page_size") int pageSize,
//                   Callback<ApiListResponse<Topic>> cb);
}
