package nz.co.roobics.contacts;

import android.app.Application;

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
                .netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
