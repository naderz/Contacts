package nz.co.roobics.contacts.contacts;

import java.util.ArrayList;

import nz.co.roobics.contacts.ViewState;
import nz.co.roobics.contacts.contacts.models.Contact;

public class ContactsViewState extends ViewState {

    private int mSelectedItem = 0;

    private ArrayList<Contact> mContacts;

    public ArrayList<Contact> getContacts() {
        return mContacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.mContacts = contacts;
    }

    public int getSelectedItem() {
        return mSelectedItem;
    }

    public void setSelectedItem(int mSelectedItem) {
        this.mSelectedItem = mSelectedItem;
    }

}
