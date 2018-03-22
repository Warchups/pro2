package com.pmm.simarro.proyectofinal_christianllopis.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pmm.simarro.proyectofinal_christianllopis.API.MiCancionOperacional;
import com.pmm.simarro.proyectofinal_christianllopis.R;
import com.pmm.simarro.proyectofinal_christianllopis.adaptador.MiFragmentPagerAdapter;

public class PantallaYoutubeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pantalla_youtube, container, false);
    }

    public void mostrarCanciones() {

        MiCancionOperacional mco = MiCancionOperacional.getInstance(getContext());

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPagerAdapter(
                getChildFragmentManager(), mco));

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

    }
}
