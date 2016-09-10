package nz.co.roobics.contacts.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.contacts.details.DetailsFragment;
import nz.co.roobics.contacts.models.Contact;

public class MainActivity extends AppCompatActivity {

    private boolean mIsLandSpace;
    private DetailsFragment mDetailsFragment;
    private ContactsFragment mContactsFragment;

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
        setupToolbar();
        mIsLandSpace = findViewById(R.id.fragment_details_container) != null;

        mContactsFragment = initContactsFragment();
        if (mIsLandSpace) {
            mDetailsFragment = initDetailsFragment();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_acc:
                mContactsFragment.sortAcc();
                return true;
            case R.id.action_sort_dec:
                mContactsFragment.sortDec();
                return true;
        }
        return false;
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
