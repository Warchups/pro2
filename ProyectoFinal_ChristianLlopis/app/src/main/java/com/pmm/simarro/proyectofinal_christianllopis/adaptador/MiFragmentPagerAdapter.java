package com.pmm.simarro.proyectofinal_christianllopis.adaptador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pmm.simarro.proyectofinal_christianllopis.API.MiCancionOperacional;
import com.pmm.simarro.proyectofinal_christianllopis.Fragments.CancionesYoutubeFragment;

public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    MiCancionOperacional mco;


    private String tabTitles[] = new String[] { "Rock", "Rap", "Pop", "Otros"};

    public MiFragmentPagerAdapter(FragmentManager fm, MiCancionOperacional mco) {
        super(fm);
        this.mco = mco;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f;
        Bundle bundle = new Bundle();

        bundle.putSerializable("TRACKS", mco.getCancionesGenero(position));

        f = new CancionesYoutubeFragment();
        f.setArguments(bundle);

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
