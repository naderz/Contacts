package nz.co.roobics.contacts.contacts.details;

import nz.co.roobics.contacts.BasePresenter;
import nz.co.roobics.contacts.BaseView;
import nz.co.roobics.contacts.models.Contact;

public class DetailsContract {

    public interface Presenter extends BasePresenter {
        void dataToPresent(Contact contact);
    }

    public interface View extends BaseView<Presenter> {

        void updateDetails(Contact contact);

        void showNoContentView();

        void hideNoContentView();

    }
}
