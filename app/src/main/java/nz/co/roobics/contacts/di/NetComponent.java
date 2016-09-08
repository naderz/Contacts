package nz.co.roobics.contacts.di;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();
}