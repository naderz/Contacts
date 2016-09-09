package nz.co.roobics.contacts.contacts;

import java.util.List;

import nz.co.roobics.contacts.BasePresenter;
import nz.co.roobics.contacts.BaseView;
import nz.co.roobics.contacts.models.Contact;

public class ContactsContract {

    interface Presenter extends BasePresenter {

        void loadContacts();

        void contactSelected(Contact contact);
    }

    interface View extends BaseView<Presenter> {

        void showContacts(List<Contact> contacts);

        void showError(String errorMessage);

        void showLoading();

        void hideLoading();

    }
}
