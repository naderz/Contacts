package nz.co.roobics.contacts.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nz.co.roobics.contacts.BaseApplication;
import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.contacts.details.DetailsActivity;
import nz.co.roobics.contacts.models.Contact;

public class ContactsFragment extends Fragment implements ContactsContract.View, ContactsAdapter.ListItemListener {
    private static final String EXTRA_VIEW_STATE = "VIEW_STATE";

    @Inject
    ContactsPresenter mPresenter;

    private ArrayList<Contact> mContacts; //I need the Serializable type so cant be List
    private ContactsAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView mInfoTextView;

    private ContactsViewState mViewState;

    public ContactsFragment() {
        // Required empty public constructor
    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void sortAcc() {
        mAdapter.sortAcc();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void sortDec() {
        mAdapter.sortDec();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mAdapter = new ContactsAdapter(this);

        DaggerContactsComponent.builder().netComponent(((BaseApplication) getActivity().getApplication()).getNetComponent())
                .contactsPresenterModule(new ContactsPresenterModule(this))
                .build().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);
        if (savedInstanceState != null) {
            mViewState = (ContactsViewState) savedInstanceState.getSerializable(EXTRA_VIEW_STATE);
            assert mViewState != null;
            mContacts = mViewState.getContacts();
        } else {
            mViewState = new ContactsViewState();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_contacts);
        mInfoTextView = (TextView) view.findViewById(R.id.tv_info);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewState.setShowingLoading(true);
                mPresenter.loadContacts();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading(mViewState.isShowingLoading());
        if (mPresenter != null) {
            mPresenter.start(mContacts);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewState.setContacts(mContacts);
        outState.putSerializable(EXTRA_VIEW_STATE, mViewState);
    }

    @Override
    public void onContactClicked(Contact contact, int position) {
        mViewState.setSelectedItem(position);
        if (((MainActivity) getActivity()).isLandSpace()) {
            ((MainActivity) getActivity()).selectContact(contact);
        } else {
            Intent detailsActivityIntent = new Intent(getActivity(), DetailsActivity.class);
            detailsActivityIntent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
            startActivity(detailsActivityIntent);
        }
    }

    @Override
    public void showContacts(@NonNull List<Contact> contacts) {
        if (getView() != null) {
            mInfoTextView.setVisibility(View.GONE);
            mAdapter.updateData(contacts);
            mContacts = (ArrayList<Contact>) mAdapter.getContacts();
            selectItemIfLandscape();
        }
    }

    private void selectItemIfLandscape() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity.isLandSpace() && !mContacts.isEmpty()) {
            activity.selectContact(mAdapter.getContacts().get(mViewState.getSelectedItem()));
        }
    }

    @Override
    public void showNoContent() {
        mContacts = new ArrayList<>();
        if (mAdapter.getItemCount() == 0) {
            mInfoTextView.setVisibility(View.VISIBLE);
            mInfoTextView.setText(getString(R.string.no_contacts));
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Connection error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(final boolean showLoading) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mViewState.setShowingLoading(showLoading);
                mSwipeRefreshLayout.setRefreshing(showLoading);
            }
        });
    }

}
