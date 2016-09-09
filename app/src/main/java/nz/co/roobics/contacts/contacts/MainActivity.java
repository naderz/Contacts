package nz.co.roobics.contacts.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import nz.co.roobics.contacts.BaseApplication;
import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.contacts.details.DetailsFragment;
import nz.co.roobics.contacts.models.Contact;

public class MainActivity extends AppCompatActivity {

    @Inject
    ContactsPresenter mContactsPresenter;

    private boolean mIsLandSpace;

    public boolean isLandSpace() {
        return mIsLandSpace;
    }

    public void selectContact(Contact contact) {
        //mDetailsPresenter.dataToPresent(contact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIsLandSpace = findViewById(R.id.fragment_details_container) != null;

        ContactsFragment contactsFragment = initContactsFragment();
        contactsFragment.setPresenter(mContactsPresenter);
        DaggerContactsComponent.builder().netComponent(((BaseApplication) getApplication()).getNetComponent())
                .contactsPresenterModule(new ContactsPresenterModule(contactsFragment))
                .build().inject(this);
//     if (mIsLandSpace) {
//            DetailsFragment detailsFragment = initDetailsFragment();
//            detailsFragment.setPresenter(mDetailsPresenter);
//            DaggerContactsDetailsComponent.builder().netComponent(((BaseApplication) getApplication()).getNetComponent())
//                    .contactsPresenterModule(new ContactsPresenterModule(contactsFragment))
//                    .detailsPresenterModule(new DetailsPresenterModule(detailsFragment))
//                    .build().inject(this);
//       }

    }

    private ContactsFragment initContactsFragment() {
        ContactsFragment contactsFragment =
                (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (contactsFragment == null) {
            contactsFragment = ContactsFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                    getSupportFragmentManager(), contactsFragment, R.id.fragment_container);
        }
        return contactsFragment;
    }

    private DetailsFragment initDetailsFragment() {
        DetailsFragment detailsFragment =
                (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_details_container);
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                    getSupportFragmentManager(), detailsFragment, R.id.fragment_details_container);
        }
        return detailsFragment;
    }
}
