package nz.co.roobics.contacts.contacts.details;

import javax.inject.Inject;

import nz.co.roobics.contacts.models.Contact;

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View mView;

    @Inject
    public DetailsPresenter(DetailsContract.View view) {
        mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
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

    @Override
    public void start() {

    }
}