package nz.co.roobics.contacts.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final List<Contact> mContacts = new ArrayList<>();
    private final ListItemListener mListener;
    private int mSelectedItem = 0;

    public ContactsAdapter(ListItemListener listener) {
        mListener = listener;
    }

    public void updateData(List<Contact> contacts) {
        mContacts.clear();
        mContacts.addAll(contacts);
        sortAcc();
    }

    public void sortAcc() {
        Collections.sort(mContacts);
    }

    public void sortDec() {
        Comparator decComparator = Collections.reverseOrder();
        Collections.sort(mContacts, decComparator);
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.contact = mContacts.get(position);
        holder.nameTextView.setText(holder.contact.getName());
        holder.emailTextView.setText(holder.contact.getEmail());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mSelectedItem = holder.getAdapterPosition();
                    mListener.onContactClicked(holder.contact, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final TextView nameTextView;
        public final TextView emailTextView;
        public Contact contact;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nameTextView = (TextView) view.findViewById(R.id.tv_contact_name);
            emailTextView = (TextView) view.findViewById(R.id.tv_contact_email);
        }
    }

    public interface ListItemListener {

        void onContactClicked(Contact contact, int position);
    }
}