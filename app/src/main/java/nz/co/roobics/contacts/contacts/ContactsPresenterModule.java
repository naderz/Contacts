package nz.co.roobics.contacts.contacts;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsPresenterModule {

    private final ContactsContract.View mView;

    public ContactsPresenterModule(ContactsContract.View view) {
        mView = view;
    }

    @Provides
    ContactsContract.View provideContactsContractView() {
        return mView;
    }

}