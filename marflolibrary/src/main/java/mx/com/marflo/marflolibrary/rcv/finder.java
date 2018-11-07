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
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        02/05/2018
 * @version : 1
 */

public interface finder {

    @NonNull
    <V extends View> V find(@IdRes int ID);
    <V extends View> V getRootView();

    void setOnClickListener(@IdRes int ID, View.OnClickListener listener);

    finder setImageWithGlide(String path, @DrawableRes int placeHolder, @IdRes int imageView);

    @NonNull
    finder setOnTouchListener(@IdRes int ID, View.OnTouchListener listener);
    @NonNull
    finder setOnLongClickListener(@IdRes int ID, View.OnLongClickListener listener);
    @NonNull
    finder setOnClickListener(@NonNull View.OnClickListener listener);
    @NonNull
    finder setOnCheckedChangeListener(@IdRes int ID, CompoundButton.OnCheckedChangeListener listener);
    @NonNull
    finder setClickable(@IdRes int ID, boolean clickable);
    @NonNull
    finder setLongClickable(@IdRes int ID, boolean clickable);
    void setText(@IdRes int ID, CharSequence text);
    @NonNull
    finder setText(@IdRes int ID, @StringRes int textID);
    @NonNull
    finder setText(@IdRes int ID, CharSequence text, TextView.BufferType type);
    @NonNull
    finder setTextSize(@IdRes int ID, float size);
    @NonNull
    finder setTypeface(@IdRes int ID, Typeface typeface, int style);
    @NonNull
    finder setTypeface(@IdRes int ID, Typeface typeface);
    @NonNull
    finder setError(@IdRes int ID, CharSequence error);
    @NonNull
    finder setError(@IdRes int ID, CharSequence error, Drawable icon);
    @NonNull
    finder setTextColor(@IdRes int ID, @ColorInt int color);
    @NonNull
    finder setTextColor(@IdRes int ID, ColorStateList colors);
    @NonNull
    finder setBackgroundColor(@IdRes int ID, @ColorInt int color);
    @NonNull
    finder setForeground(@IdRes int ID, Drawable background);
    @NonNull
    finder setBackground(@IdRes int ID, Drawable background);
    @NonNull
    finder setBackgroundDrawable(@IdRes int ID, Drawable background);
    @NonNull
    finder setBackgroundResource(@IdRes int ID, @DrawableRes int background);
    @NonNull
    finder setImageDrawable(@IdRes int ID, @Nullable Drawable drawable);
    @NonNull
    finder setImageResource(@IdRes int ID, @DrawableRes int resId);
    @NonNull
    finder setImageURI(@IdRes int ID, @Nullable Uri uri);
    @NonNull
    finder setImageBitmap(@IdRes int ID, Bitmap bm);
    @NonNull
    finder setColorFilter(@IdRes int ID, ColorFilter cf);
    @NonNull
    finder setColorFilter(@IdRes int ID, int color, PorterDuff.Mode mode);
    @NonNull
    finder clearColorFilter(@IdRes int ID);
    @NonNull
    finder setVisible(@IdRes int ID, boolean visible);
    @NonNull
    finder setInvisible(@IdRes int ID, boolean invisible);
    @NonNull
    finder setGone(@IdRes int ID, boolean gone);
    @NonNull
    finder setVisibility(@IdRes int ID, int visibility);
    @NonNull
    finder setAlpha(@IdRes int ID, float alpha);
    @NonNull
    finder addView(@IdRes int ID, View child);
    @NonNull
    finder addView(@IdRes int ID, View child, int index);
    @NonNull
    finder removeView(@IdRes int ID, View view);
    @NonNull
    finder removeViewAt(@IdRes int ID, int index);
    @NonNull
    finder removeAllViews(@IdRes int ID);
    @NonNull
    finder setEnabled(@IdRes int ID, boolean enable);
    @NonNull
    finder setEnabled(@IdRes int ID);
    @NonNull
    finder setDisabled(@IdRes int ID);
    @NonNull
    finder setTag(@IdRes int ID, Object tag);
    @NonNull
    finder setTag(@IdRes int ID, int key, Object tag);
    @NonNull
    finder setChecked(@IdRes int ID, boolean checked);
    @NonNull
    finder setSelected(@IdRes int ID, boolean selected);
    @NonNull
    finder setPressed(@IdRes int ID, boolean pressed);
    @NonNull
    finder setProgress(@IdRes int ID, int progress);
    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)
    finder setProgress(@IdRes int ID, int progress, boolean animate);
    @NonNull
    finder setMaxProgress(@IdRes int ID, int max);
    @NonNull
    finder setProgress(@IdRes int ID, int progress, int max);
    @NonNull
    finder setRating(@IdRes int ID, float rating);
    @NonNull
    finder setMaxRating(@IdRes int ID, int max);
    @NonNull
    finder setRating(@IdRes int ID, float rating, int max);
    @NonNull
    finder setAdapter(@IdRes int ID, Adapter adapter);
    @NonNull
    finder setOnItemClickListener(@IdRes int ID, AdapterView.OnItemClickListener listener);
    @NonNull
    finder setOnItemLongClickListener(@IdRes int ID, AdapterView.OnItemLongClickListener listener);
    @NonNull
    finder setOnItemSelectedListener(@IdRes int ID, AdapterView.OnItemSelectedListener listener);

}
