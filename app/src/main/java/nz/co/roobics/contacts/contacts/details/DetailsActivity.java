package nz.co.roobics.contacts.contacts.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import nz.co.roobics.contacts.FragmentUtils;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "CONTACT";

    @Inject
    DetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Contact contact = (Contact) getIntent().getSerializableExtra(EXTRA_CONTACT);
        DetailsFragment detailsFragment = initDetailsFragment(contact);
        detailsFragment.setPresenter(mPresenter);

        DaggerDetailsComponent.builder()
                .detailsPresenterModule(new DetailsPresenterModule(detailsFragment)).build()
                .inject(this);

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
}
