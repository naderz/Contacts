
package nz.co.roobics.contacts.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {

    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("address")
    private Address mAddress;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("website")
    private String mWebsite;
    @SerializedName("company")
    private Company mCompany;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
        this.mAddress = address;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        this.mWebsite = website;
    }

    public Company getCompany() {
        return mCompany;
    }

    public void setCompany(Company company) {
        this.mCompany = company;
    }

    @Override
    public int compareTo(@NonNull Contact contact) {
        return getName().compareTo(contact.getName());
    }

}
