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

    public void setView(ContactsContract.View view) {
        mView = view;
    }

    @Override
    public void loadContacts() {
        mView.showLoading(true);
        mContactsApi.getContacts(this);
    }

    @Override
    public void start(List<Contact> contacts) {
        if (contacts != null) {
            if (contacts.isEmpty()) {
                mView.showNoContent();
            } else {
                mView.showContacts(contacts);
            }
        } else {
            loadContacts();
        }
    }

    @Override
    public void start() {
        //Nothing to do here
    }

    @Override
    public void onSuccess(List<Contact> contacts) {
        mView.showLoading(false);
        if (contacts == null || contacts.isEmpty()) {
            mView.showNoContent();
        } else {
            mView.showContacts(contacts);
        }
    }

    @Override
    public void onFailure() {
        mView.showLoading(false);
        mView.showError();
        mView.showNoContent();
    }
}
