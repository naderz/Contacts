package nz.co.roobics.contacts.contacts;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class ContactsFragment extends Fragment implements ContactsContract.View, ContactsAdapter.ListItemListener {

    private ContactsContract.Presenter mPresenter;
    private ContactsAdapter.ListItemListener mListener;

    private List<Contact> mContacts;
    private ContactsAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mEmptyView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_contacts);
        mEmptyView = view.findViewById(R.id.view_empty);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadContacts();
            }
        });

        return view;
    }

    @Override
    public void onContactClicked(Contact contact) {
        //TODO:
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        if (getView() != null) {
            mContacts = contacts;
            if (mContacts == null || mContacts.isEmpty()) {
                mEmptyView.setVisibility(View.VISIBLE);
                mAdapter = new ContactsAdapter(mListener);
            } else {
                mEmptyView.setVisibility(View.GONE);
                if (mAdapter == null) {
                    mAdapter = new ContactsAdapter(this, mContacts);
                } else {
                    mAdapter.updateData(mContacts);
                }
            }
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void showError(String errorMessage) {
        mEmptyView.setVisibility(View.VISIBLE);
        //TODO: Show a snack here ?
    }

    @Override
    public void showLoading() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

    }

    @Override
    public void hideLoading() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setPresenter(ContactsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
