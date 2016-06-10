package no.txcb.sample;

import android.app.Application;
import android.content.Context;

import no.txcb.sample.modules.AndroidModule;

public class MainApplication extends Application {


    private static MainApplication instance;

    private MainComponent mainComponent;

    public static Context getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        mainComponent = DaggerMainComponent.builder()
                .build();
        instance = this;
    }

    public static MainComponent component(Context context) {
        return ((MainApplication) context.getApplicationContext()).mainComponent;
    }

}
