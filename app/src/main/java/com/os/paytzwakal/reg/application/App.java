package com.os.paytzwakal.reg.application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.view.Window;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.os.paytzwakal.reg.database.RegistrationDatabase;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends MultiDexApplication {
    public static Context APP_CONTEXT;
    public static boolean activityVisible;
    public static App mInstance;
    private static Retrofit retrofit;
    private static Retrofit new_retrofit;
    private static String MD5_Hash_String;
    private static String Main_BASE_URL = "https://madinapay.co.tz/web_services_wakala/";
    private Window window;

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(150 * 1024 * 1024); // 150 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(config.build());
    }


    public static Retrofit getInitialClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(logging);
            builder.connectTimeout(180, TimeUnit.SECONDS);
            builder.readTimeout(180, TimeUnit.SECONDS);
            builder.writeTimeout(180, TimeUnit.SECONDS);

            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Main_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
        APP_CONTEXT = getApplicationContext();
        RegistrationDatabase db = Room.databaseBuilder(getApplicationContext(),
                RegistrationDatabase.class, "tokendatabase").build();
        Config.init(APP_CONTEXT);

        initImageLoader(this);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                throwable.printStackTrace();
                System.exit(1);
            }
        });
    }


}






