package nz.co.roobics.contacts.contacts.details;

import dagger.Module;
import dagger.Provides;
import nz.co.roobics.contacts.contacts.ContactsContract;

@Module
public class DetailsPresenterModule {

    private final DetailsContract.View mView;

    public DetailsPresenterModule(DetailsContract.View view) {
        mView = view;
    }

    @Provides
    DetailsContract.View provideDetailsContractView() {
        return mView;
    }

}