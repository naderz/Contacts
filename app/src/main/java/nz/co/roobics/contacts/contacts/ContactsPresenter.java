package nz.co.roobics.contacts.contacts;

import java.util.List;

import javax.inject.Inject;

import nz.co.roobics.contacts.contacts.services.ContactsApi;
import nz.co.roobics.contacts.models.Contact;

public class ContactsPresenter implements ContactsContract.Presenter, ContactsApi.ContactsResponse {

    private ContactsContract.View mView;
    private ContactsApi mContactsApi;

    @Inject
    public ContactsPresenter(ContactsContract.View view, ContactsApi contactsApi) {
        mView = view;
        mContactsApi = contactsApi;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void loadContacts() {
        mView.showLoading();
        mContactsApi.getContacts(this);
    }

    @Override
    public void start() {
        loadContacts();
    }

    @Override
    public void onSuccess(List<Contact> contacts) {
        mView.hideLoading();
        mView.showContacts(contacts);
    }

    @Override
    public void onFailure() {
        mView.hideLoading();
        mView.showError("");
    }
}
