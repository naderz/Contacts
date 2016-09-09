package nz.co.roobics.contacts.contacts.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class DetailsFragment extends Fragment implements DetailsContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private DetailsContract.Presenter mPresenter;
    private TextView mUsernameTextView;
    private TextView mPhoneTextView;
    private TextView mAddressTextView;
    private TextView mWebsiteTextView;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        return view;
    }

    @Override
    public void showDetails(Contact contact) {
        mUsernameTextView.setText(contact.getUsername());
        mPhoneTextView.setText(contact.getPhone());
        mAddressTextView.setText(contact.getAddress().getCity()); //TODO Make the address look right
        mWebsiteTextView.setText(contact.getWebsite());
    }

    @Override
    public void showEmpty() {
        //TODO Remove the view if there is no data to show
        //TODO This scenario will only happen when we start the activity on landscape
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }
}
