package nz.co.roobics.contacts;

import java.io.Serializable;

public class ViewState implements Serializable {

    private boolean mIsShowingLoading;

    public boolean isShowingLoading() {
        return mIsShowingLoading;
    }

    public void setShowingLoading(boolean showingLoading) {
        mIsShowingLoading = showingLoading;
    }

}
