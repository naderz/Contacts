package nz.co.roobics.contacts;

import android.app.Application;

import nz.co.roobics.contacts.di.ApplicationModule;
import nz.co.roobics.contacts.di.DaggerNetComponent;
import nz.co.roobics.contacts.di.NetComponent;
import nz.co.roobics.contacts.di.NetModule;

public class BaseApplication extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mNetComponent = DaggerNetComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }


}
