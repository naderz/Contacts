package nz.co.roobics.contacts.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import nz.co.roobics.contacts.BaseApplication;
import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;

public class MainActivity extends AppCompatActivity {

    @Inject
    ContactsPresenter mContactsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactsFragment contactsFragment =
                (ContactsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (contactsFragment == null) {
            // Create the fragment
            contactsFragment = ContactsFragment.newInstance();
            FragmentUtils.addFragmentToActivity(
                    getSupportFragmentManager(), contactsFragment, R.id.fragment_container);
        }

        contactsFragment.setPresenter(mContactsPresenter);

        DaggerContactsComponent.builder()
                .netComponent(((BaseApplication) getApplication()).getNetComponent())
                .contactsPresenterModule(new ContactsPresenterModule(contactsFragment)).build()
                .inject(this);

    }
}
