package nz.co.roobics.contacts.contacts.services;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import nz.co.roobics.contacts.di.FragmentScoped;
import nz.co.roobics.contacts.models.Contact;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

@Module
public class ContactsApiModule {

    public interface ContactsServices {
        @GET("users")
        Call<List<Contact>> getContacts();
    }

    @Provides
    @FragmentScoped // needs to be consistent with the component scope
    public ContactsApi providesContactsApi(Retrofit retrofit) {
        return new ContactsApi(retrofit);
    }
}
