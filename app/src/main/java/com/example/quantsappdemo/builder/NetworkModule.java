package com.example.quantsappdemo.builder;

import android.content.Context;
import android.util.Log;

import com.example.quantsappdemo.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String AUTH_HEADER = "Authorization";
    public static final String X_AUTH_TOKEN = "X-Auth-Token";
    private static final int HTTP_READ_TIMEOUT = 25;
    private static final int HTTP_WRITE_TIMEOUT = 25;
    private static final int HTTP_CONNECT_TIMEOUT = 25;

    private static String TAG ="NetworkModule : ";



    @AppScope
    @Provides
    OkHttpClient provideHttpClient(Interceptor keyIntercepter, HttpLoggingInterceptor logger, Cache cache) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logger);
        }

        builder.addInterceptor(keyIntercepter);
        builder.cache(cache);
        builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MINUTES);
        builder.writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.MINUTES);
        builder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MINUTES);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       // okHttpClient.interceptors().add(logging);

        return httpLoggingInterceptor;
    }

    @AppScope
    @Provides
    Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
               /* Request request = chain.request().newBuilder().addHeader(AUTH_HEADER, "APIKEY 7047874e-4f85-11e7-b114-b2f933d5fe66")
                        .addHeader("X-TZ", GeneralUtil.getTimeZoneOffset())
                        .build();*/

                Request original = chain.request();
                HttpUrl httpUrl = original.url();

                HttpUrl newHttpUrl = httpUrl.newBuilder().build();

                Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                        //.addHeader("application/json","x-www-form-urlencoded");
               // request = requestBuilder = requestBuilder.build();
                Request request = requestBuilder.build();

                Log.d("url ==== ",""+request.url());


                return chain.proceed(request);
            }
        };
    }


    @AppScope
    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 1024 * 1024);
    }

    @AppScope
    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @AppScope
    @Provides
    RxJava2CallAdapterFactory provideRxAdapter() {
        return RxJava2CallAdapterFactory.create();
    }


    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }

}
