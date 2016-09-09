package nz.co.roobics.contacts.contacts.details;

import nz.co.roobics.contacts.BasePresenter;
import nz.co.roobics.contacts.BaseView;
import nz.co.roobics.contacts.models.Contact;

public class DetailsContract {

    interface Presenter extends BasePresenter {

        void dataToPresent(Contact contact);

    }

    interface View extends BaseView<Presenter> {

        void showDetails(Contact contact);

        void showEmpty();

    }
}
