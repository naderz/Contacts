package nz.co.roobics.contacts.contacts.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class DetailsFragment extends Fragment implements DetailsContract.View {
    private static final String EXTRA_CONTACT = "CONTACT";

    @Inject
    DetailsPresenter mPresenter;

    private TextView mUsernameTextView;
    private TextView mPhoneTextView;
    private TextView mAddressTextView;
    private TextView mWebsiteTextView;
    private View mDetailsContainerView;
    private View mEmptyView;

    private Contact mContact;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Contact contact) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    public DetailsContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDetailsComponent.builder()
                .detailsPresenterModule(new DetailsPresenterModule(this)).build()
                .inject(this);

        if (getArguments() != null) {
            mContact = (Contact) getArguments().getSerializable(EXTRA_CONTACT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = new Bundle();
        outState.putSerializable(EXTRA_CONTACT, mContact);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mUsernameTextView = (TextView) view.findViewById(R.id.tv_details_username);
        mPhoneTextView = (TextView) view.findViewById(R.id.tv_details_phone);
        mAddressTextView = (TextView) view.findViewById(R.id.tv_details_address);
        mWebsiteTextView = (TextView) view.findViewById(R.id.tv_details_website);
        mDetailsContainerView = view.findViewById(R.id.ll_details_content);
        mEmptyView = view.findViewById(R.id.view_details_empty);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.dataToPresent(mContact);
    }

    @Override
    public void updateDetails(Contact contact) {
        mUsernameTextView.setText(contact.getUsername());
        mPhoneTextView.setText(contact.getPhone());
        mAddressTextView.setText(contact.getAddress().getCity()); //TODO Make the address look right
        mWebsiteTextView.setText(contact.getWebsite());
    }

    @Override
    public void showNoContentView() {
        mDetailsContainerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoContentView() {
        mDetailsContainerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }
}
