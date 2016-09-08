package nz.co.roobics.contacts.contacts.services;

import java.util.List;

import nz.co.roobics.contacts.models.Contact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactsServices {

    @GET("users")
    Call<List<Contact>> getContacts();
}
