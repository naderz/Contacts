package nz.co.roobics.contacts;

import java.io.Serializable;

public class ViewState implements Serializable {

    /**
     * Used to indicate that loading is currently displayed by the View
     */
    private boolean mIsShowingLoading;

    public boolean isShowingLoading() {
        return mIsShowingLoading;
    }

    public void setShowingLoading(boolean showingLoading) {
        mIsShowingLoading = showingLoading;
    }

}
