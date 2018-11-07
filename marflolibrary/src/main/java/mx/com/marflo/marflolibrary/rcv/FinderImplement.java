package mx.com.marflo.marflolibrary.rcv;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import mx.com.marflo.marflolibrary.utils;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        02/05/2018
 * @version : 1
 */

public class FinderImplement implements finder {
    @NonNull
    private final SparseArray<View> mCachedViews = new SparseArray<>();
    @NonNull
    private final View mItemView;

    public FinderImplement(@NonNull final View itemView) {
        mItemView = itemView;
    }

    @NonNull
    @Override
    public <V extends View> V find(final int ID) {
        return (V) findViewById(ID);
    }

    public <V extends View> V getRootView() {
        return (V) mItemView;
    }

    @NonNull
    @Override
    public void setOnClickListener(final int ID, final View.OnClickListener listener) {
        findViewById(ID).setOnClickListener(listener);
    }

    @Override
    public finder setImageWithGlide(String path, int placeHolder, int imageView) {
        ImageView imv = find(imageView);
        imv.setVisibility(View.VISIBLE);
        utils.putImageWithGlide(path, imv, placeHolder);
        return this;
    }

    @NonNull
    @Override
    public finder setOnTouchListener(final int ID, final View.OnTouchListener listener) {
        find(ID).setOnTouchListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setOnLongClickListener(final int ID, final View.OnLongClickListener listener) {
        find(ID).setOnLongClickListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setOnClickListener(@NonNull final View.OnClickListener listener) {
        getRootView().setOnClickListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setOnCheckedChangeListener(final int ID, final CompoundButton.OnCheckedChangeListener listener) {
        ((CompoundButton) find(ID)).setOnCheckedChangeListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setClickable(final int ID, final boolean clickable) {
        find(ID).setClickable(clickable);
        return this;
    }

    @NonNull
    @Override
    public finder setLongClickable(final int ID, final boolean clickable) {
        find(ID).setLongClickable(clickable);
        return this;
    }

    @NonNull
    @Override
    public void setText(final int ID, final CharSequence text) {
        ((TextView) find(ID)).setText(text);
    }

    @NonNull
    @Override
    public finder setText(final int ID, @StringRes final int textID) {
        ((TextView) find(ID)).setText(textID);
        return this;
    }

    @NonNull
    @Override
    public finder setText(final int ID, final CharSequence text, final TextView.BufferType type) {
        ((TextView) find(ID)).setText(text, type);
        return this;
    }

    @NonNull
    @Override
    public finder setTextSize(final int ID, final float size) {
        ((TextView) find(ID)).setTextSize(size);
        return this;
    }

    @NonNull
    @Override
    public finder setTypeface(final int ID, final Typeface typeface, final int style) {
        ((TextView) find(ID)).setTypeface(typeface, style);
        return this;
    }

    @NonNull
    @Override
    public finder setTypeface(final int ID, final Typeface typeface) {
        ((TextView) find(ID)).setTypeface(typeface);
        return this;
    }

    @NonNull
    @Override
    public finder setError(final int ID, final CharSequence error) {
        ((TextView) find(ID)).setError(error);
        return this;
    }

    @NonNull
    @Override
    public finder setError(final int ID, final CharSequence error, final Drawable icon) {
        ((TextView) find(ID)).setError(error, icon);
        return this;
    }

    @NonNull
    @Override
    public finder setTextColor(final int ID, final int color) {
        ((TextView) find(ID)).setTextColor(color);
        return this;
    }

    @NonNull
    @Override
    public finder setTextColor(final int ID, final ColorStateList colors) {
        ((TextView) find(ID)).setTextColor(colors);
        return this;
    }

    @NonNull
    @Override
    public finder setBackgroundColor(final int ID, @ColorInt final int color) {
        find(ID).setBackgroundColor(color);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public finder setForeground(final int ID, final Drawable background) {
        find(ID).setForeground(background);
        return this;
    }

    @NonNull
    @Override
    public finder setBackground(final int ID, final Drawable background) {
        find(ID).setBackgroundDrawable(background);
        return this;
    }

    @NonNull
    @Override
    public finder setBackgroundDrawable(final int ID, final Drawable background) {
        return setBackground(ID, background);
    }

    @NonNull
    @Override
    public finder setBackgroundResource(final int ID, @DrawableRes final int background) {
        find(ID).setBackgroundResource(background);
        return this;
    }

    @NonNull
    @Override
    public finder setImageDrawable(final int ID, @Nullable final Drawable drawable) {
        ((ImageView) find(ID)).setImageDrawable(drawable);
        return this;
    }

    @NonNull
    @Override
    public finder setImageResource(final int ID, final int resId) {
        ((ImageView) find(ID)).setImageResource(resId);
        return this;
    }

    @NonNull
    @Override
    public finder setImageURI(final int ID, @Nullable final Uri uri) {
        ((ImageView) find(ID)).setImageURI(uri);
        return this;
    }

    @NonNull
    @Override
    public finder setImageBitmap(final int ID, final Bitmap bm) {
        ((ImageView) find(ID)).setImageBitmap(bm);
        return this;
    }

    @NonNull
    @Override
    public finder setColorFilter(final int ID, final ColorFilter cf) {
        ((ImageView) find(ID)).setColorFilter(cf);
        return this;
    }

    @NonNull
    @Override
    public finder setColorFilter(final int ID, final int color, final PorterDuff.Mode mode) {
        ((ImageView) find(ID)).setColorFilter(color, mode);
        return this;
    }

    @NonNull
    @Override
    public finder clearColorFilter(final int ID) {
        ((ImageView) find(ID)).clearColorFilter();
        return this;
    }

    @NonNull
    @Override
    public finder setVisible(final int ID, final boolean visible) {
        find(ID).setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    @NonNull
    @Override
    public finder setInvisible(final int ID, final boolean invisible) {
        find(ID).setVisibility(invisible ? View.INVISIBLE : View.VISIBLE);
        return this;
    }

    @NonNull
    @Override
    public finder setGone(final int ID, final boolean gone) {
        find(ID).setVisibility(gone ? View.GONE : View.VISIBLE);
        return this;
    }

    @NonNull
    @Override
    public finder setVisibility(final int ID, final int visibility) {
        find(ID).setVisibility(visibility);
        return this;
    }

    @NonNull
    @Override
    public finder setAlpha(final int ID, final float alpha) {
        find(ID).setAlpha(alpha);
        return this;
    }

    @NonNull
    @Override
    public finder addView(final int ID, final View child) {
        ((ViewGroup) find(ID)).addView(child);
        return this;
    }

    @NonNull
    @Override
    public finder addView(final int ID, final View child, final int index) {
        ((ViewGroup) find(ID)).addView(child, index);
        return this;
    }

    @NonNull
    @Override
    public finder removeView(final int ID, final View view) {
        ((ViewGroup) find(ID)).removeView(view);
        return this;
    }

    @NonNull
    @Override
    public finder removeViewAt(final int ID, final int index) {
        ((ViewGroup) find(ID)).removeViewAt(index);
        return this;
    }

    @NonNull
    @Override
    public finder removeAllViews(final int ID) {
        ((ViewGroup) find(ID)).removeAllViews();
        return this;
    }

    @NonNull
    @Override
    public finder setEnabled(final int ID, final boolean enable) {
        find(ID).setEnabled(enable);
        return this;
    }

    @NonNull
    @Override
    public finder setEnabled(final int ID) {
        find(ID).setEnabled(true);
        return this;
    }

    @NonNull
    @Override
    public finder setDisabled(final int ID) {
        find(ID).setEnabled(false);
        return this;
    }

    @NonNull
    @Override
    public finder setTag(final int ID, final Object tag) {
        find(ID).setTag(tag);
        return this;
    }

    @NonNull
    @Override
    public finder setTag(final int ID, final int key, final Object tag) {
        find(ID).setTag(key, tag);
        return this;
    }

    @NonNull
    @Override
    public finder setChecked(final int ID, final boolean checked) {
        ((Checkable) find(ID)).setChecked(checked);
        return this;
    }

    @NonNull
    @Override
    public finder setSelected(final int ID, final boolean selected) {
        find(ID).setSelected(selected);
        return this;
    }

    @NonNull
    @Override
    public finder setPressed(final int ID, final boolean pressed) {
        find(ID).setPressed(pressed);
        return this;
    }

    @NonNull
    @Override
    public finder setProgress(final int ID, final int progress) {
        ((ProgressBar) find(ID)).setProgress(progress);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public finder setProgress(final int ID, final int progress, final boolean animate) {
        ((ProgressBar) find(ID)).setProgress(progress, animate);
        return this;
    }

    @NonNull
    @Override
    public finder setMaxProgress(final int ID, final int max) {
        ((ProgressBar) find(ID)).setMax(max);
        return this;
    }

    @NonNull
    @Override
    public finder setProgress(final int ID, final int progress, final int max) {
        final ProgressBar progressBar = find(ID);
        progressBar.setMax(max);
        progressBar.setProgress(progress);
        return this;
    }

    @NonNull
    @Override
    public finder setRating(final int ID, final float rating) {
        ((RatingBar) find(ID)).setRating(rating);
        return this;
    }

    @NonNull
    @Override
    public finder setMaxRating(final int ID, final int max) {
        ((RatingBar) find(ID)).setMax(max);
        return this;
    }

    @NonNull
    @Override
    public finder setRating(final int ID, final float rating, final int max) {
        final RatingBar ratingBar = find(ID);
        ratingBar.setRating(rating);
        ratingBar.setMax(max);
        return this;
    }

    @NonNull
    @Override
    public finder setAdapter(final int ID, final Adapter adapter) {
        ((AdapterView) find(ID)).setAdapter(adapter);
        return this;
    }

    @NonNull
    @Override
    public finder setOnItemClickListener(final int ID, final AdapterView.OnItemClickListener listener) {
        ((AdapterView) find(ID)).setOnItemClickListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setOnItemLongClickListener(final int ID, final AdapterView.OnItemLongClickListener listener) {
        ((AdapterView) find(ID)).setOnItemLongClickListener(listener);
        return this;
    }

    @NonNull
    @Override
    public finder setOnItemSelectedListener(final int ID, final AdapterView.OnItemSelectedListener listener) {
        ((AdapterView) find(ID)).setOnItemSelectedListener(listener);
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
