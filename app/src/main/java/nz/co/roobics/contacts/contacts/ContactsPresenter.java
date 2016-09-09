package nz.co.roobics.contacts.contacts;

import javax.inject.Inject;

import nz.co.roobics.contacts.models.Contact;

public class ContactsPresenter implements ContactsContract.Presenter {

    ContactsContract.View mView;

    @Inject
    public ContactsPresenter(ContactsContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void loadContacts() {
        mView.showLoading();
        //TODO: Repository call get Contacts
    }

    @Override
    public void contactSelected(Contact contact) {
        //TODO dont think we need this
    }

    @Override
    public void start() {
        loadContacts();
    }
}
