package nz.co.roobics.contacts.di;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nz.co.roobics.contacts.BaseApplication;

@Module
public class ApplicationModule {

    private BaseApplication mApplication;

    public ApplicationModule(BaseApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

}
