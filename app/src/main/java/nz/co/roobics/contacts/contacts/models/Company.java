
package nz.co.roobics.contacts.contacts.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Company implements Serializable{

    @SerializedName("name")
    private String mName;
    @SerializedName("catchPhrase")
    private String mCatchPhrase;
    @SerializedName("bs")
    private String mBs;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCatchPhrase() {
        return mCatchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.mCatchPhrase = catchPhrase;
    }

    public String getBs() {
        return mBs;
    }

    public void setBs(String bs) {
        this.mBs = bs;
    }


}
