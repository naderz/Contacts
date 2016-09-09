package nz.co.roobics.contacts.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nz.co.roobics.contacts.R;
import nz.co.roobics.contacts.models.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final List<Contact> mContacts = new ArrayList<>();
    private final ListItemListener mListener;

    public ContactsAdapter(ListItemListener listener) {
        mListener = listener;
    }

    public ContactsAdapter(ListItemListener listener, List<Contact> contacts) {
        mListener = listener;
        mContacts.addAll(contacts);
    }

    public void updateData(List<Contact> contacts) {
        mContacts.clear();
        mContacts.addAll(contacts);
    }

    //TODO might need sorting methods to be here

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.contact = mContacts.get(position);
        holder.usernameTextView.setText(holder.contact.getUsername());
        holder.emailTextView.setText(holder.contact.getEmail());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onContactClicked(holder.contact);
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
        public final TextView usernameTextView;
        public final TextView emailTextView;
        public Contact contact;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            usernameTextView = (TextView) view.findViewById(R.id.tv_contact_name);
            emailTextView = (TextView) view.findViewById(R.id.tv_contact_email);
        }
    }

    public interface ListItemListener {

        void onContactClicked(Contact contact);
    }
}