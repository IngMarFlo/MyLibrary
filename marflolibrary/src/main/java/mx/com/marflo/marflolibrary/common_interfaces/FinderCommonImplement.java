package mx.com.marflo.marflolibrary.common_interfaces;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mx.com.marflo.marflolibrary.utils;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        06/07/2018
 * @version : 1
 */
public class FinderCommonImplement implements finderCommon {
    private final SparseArray<View> mCachedViews = new SparseArray<>();
    private final View mItemView;

    public FinderCommonImplement(@NonNull final View itemView) {
        mItemView = itemView;
    }

    private  <V extends View> V find(final int ID) {
        return (V) findViewById(ID);
    }

    @Override
    public <V extends View> V getRootView() {
        return (V) mItemView;
    }

    @Override
    public finderCommon setImageWithGlide(String path, int placeHolder, int imageView) {
        ImageView imv = find(imageView);
        utils.putImageWithGlide(path, imv, placeHolder);
        return this;
    }

    @NonNull
    @Override
    public finderCommon setText(int ID, CharSequence text) {
        ((TextView) find(ID)).setText(text);
        return this;
    }

    @NonNull
    @Override
    public finderCommon setText(int ID, int textID) {
        ((TextView) find(ID)).setText(textID);
        return this;
    }

    @NonNull
    @Override
    public finderCommon setText(int ID, CharSequence text, TextView.BufferType type) {
        ((TextView) find(ID)).setText(text, type);
        return this;
    }

    @Override
    public finderCommon setVisibility(int ID, int visibility) {
        find(ID).setVisibility(visibility);
        return this;
    }

    @NonNull
    private View findViewById(@IdRes final int ID) {
        final View cachedView = mCachedViews.get(ID);
        if (cachedView != null) {
            return cachedView;
        }
        final View view = mItemView.findViewById(ID);
        mCachedViews.put(ID, view);
        return view;
    }
}
