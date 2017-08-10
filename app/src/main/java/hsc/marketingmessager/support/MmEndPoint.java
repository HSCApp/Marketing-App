package hsc.marketingmessager.support;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hoang Ha on 8/1/2017.
 */

public class MmEndPoint {
    private static MmEndPoint mmEndPoint;
    private static long TIME_OUT = 120;
    private static long TIME_READ_OUT = 120;
    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit;
    private static Context mContext;
    private static String HOST = "http://xehoihanoi.com.vn/";
    private static String token;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;

    public static Retrofit getInstance(Context context) {
        if (mmEndPoint == null) {
            mContext = context.getApplicationContext();
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(TIME_READ_OUT, java.util.concurrent.TimeUnit.SECONDS);
            builder = new Retrofit.Builder();
        }
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("token", token);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        builder.baseUrl(HOST)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson));
        return retrofit;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MmEndPoint.token = token;
    }
}
