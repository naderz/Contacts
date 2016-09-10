package nz.co.roobics.contacts.contacts.details;

import javax.inject.Inject;

import nz.co.roobics.contacts.contacts.models.Contact;

public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View mView;

    @Inject
    public DetailsPresenter(DetailsContract.View view) {
        mView = view;
    }

    @Override
    public void dataToPresent(Contact contact) {
        if (contact != null) {
            mView.hideNoContentView();
            mView.updateDetails(contact);
        } else {
            mView.showNoContentView();
        }
    }

}
