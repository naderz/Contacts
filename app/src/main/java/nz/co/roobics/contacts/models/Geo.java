
package nz.co.roobics.contacts.models;

import com.google.gson.annotations.SerializedName;

public class Geo {

    @SerializedName("lat")
    private String mLat;
    @SerializedName("lng")
    private String mLng;

    /**
     * @return The mLat
     */
    public String getLat() {
        return mLat;
    }

    /**
     * @param lat The mLat
     */
    public void setLat(String lat) {
        this.mLat = lat;
    }

    /**
     * @return The mLng
     */
    public String getLng() {
        return mLng;
    }

    /**
     * @param lng The mLng
     */
    public void setLng(String lng) {
        this.mLng = lng;
    }

}
