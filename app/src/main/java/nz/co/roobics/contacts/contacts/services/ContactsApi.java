package nz.co.roobics.contacts.contacts.services;

import java.util.List;

import javax.inject.Inject;

import nz.co.roobics.contacts.models.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactsApi {

    private Retrofit mRetrofit;

    @Inject
    public ContactsApi(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public void getContacts(final ContactsResponse contactsResponse) {
        mRetrofit.create(ContactsApiModule.ContactsServices.class).getContacts().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contactsResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                contactsResponse.onFailure();
            }
        });
    }

    public interface ContactsResponse {

        void onSuccess(List<Contact> contacts);

        void onFailure();

    }

}
