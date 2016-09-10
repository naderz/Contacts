package nz.co.roobics.contacts.contacts.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "CONTACT";
    private boolean mIsLandSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsLandSpace = findViewById(R.id.fragment_details_container) != null;

        Contact contact = (Contact) getIntent().getSerializableExtra(EXTRA_CONTACT);
        initDetailsFragment(contact);
    }

    private DetailsFragment initDetailsFragment(Contact contact) {
        DetailsFragment detailsFragment =
                (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance(contact);
            FragmentUtils.addFragmentToActivity(
                    getSupportFragmentManager(), detailsFragment, R.id.fragment_container);
        }
        return detailsFragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!mIsLandSpace) {
            finish();
        }

    }
}
