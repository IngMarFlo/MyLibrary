package mx.com.marflo.marflolibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        10/01/2018
 * Descripción:
 */

public class FragmentsPageAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;
    private FragmentsAdapterCallback callback;

    public FragmentsPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments, FragmentsAdapterCallback callback) {
        super(fm);
        this.fragments = fragments;
        this.callback = callback;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return callback.pageTitle(position);
    }

    public interface FragmentsAdapterCallback{
        String pageTitle(int position);
    }
}
