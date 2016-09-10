package nz.co.roobics.contacts.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.contacts.details.DetailsFragment;
import nz.co.roobics.contacts.models.Contact;

public class MainActivity extends AppCompatActivity {

    private boolean mIsLandSpace;
    private DetailsFragment mDetailsFragment;

    public boolean isLandSpace() {
        return mIsLandSpace;
    }

    public void selectContact(Contact contact) {
        mDetailsFragment.getPresenter().dataToPresent(contact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIsLandSpace = findViewById(R.id.fragment_details_container) != null;

        initContactsFragment();
        if (mIsLandSpace) {
            mDetailsFragment = initDetailsFragment();
        }

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
