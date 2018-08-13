package mx.com.marflo.marflolibrary.common_interfaces;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        06/07/2018
 * Descripción:
 */
public interface finderCommon {

    <V extends View> V getRootView();

    finderCommon setImageWithGlide(String path, @DrawableRes int placeHolder, @IdRes int imageView);

    @NonNull
    finderCommon setText(@IdRes int ID, CharSequence text);

    @NonNull
    finderCommon setText(@IdRes int ID, @StringRes int textID);

    @NonNull
    finderCommon setText(@IdRes int ID, CharSequence text, TextView.BufferType type);

    finderCommon setVisibility(@IdRes int ID, int visibility);
}
