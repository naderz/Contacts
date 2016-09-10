package nz.co.roobics.contacts.contacts;

import android.support.annotation.NonNull;

import java.util.List;

import nz.co.roobics.contacts.BasePresenter;
import nz.co.roobics.contacts.BaseView;
import nz.co.roobics.contacts.models.Contact;

public class ContactsContract {

    interface Presenter extends BasePresenter {

        void loadContacts();

        void start(List<Contact> contacts);
    }

    interface View extends BaseView {

        void showContacts(@NonNull List<Contact> contacts);

        void showNoContent();

        void showError();

        void showLoading(boolean showLoading);

    }
}
