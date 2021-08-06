+
,package com.axweb.spotyficlone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axweb.spotyficlone.R
import com.axweb.spotyficlone.fragmentsTaps.AlbunsFragment
import com.axweb.spotyficlone.fragmentsTaps.ArtistasFragment
import com.axweb.spotyficlone.fragmentsTaps.PlaylistFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_biblioteca.*


class BibliotecaFragment : Fragment() {

    companion object {
        fun newInstance(): BibliotecaFragment {
            val fragmentBiblioteca = BibliotecaFragment()
            val argumentos = Bundle()
            fragmentBiblioteca.arguments = argumentos
            return fragmentBiblioteca
        }
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            return inflater.inflate(R.layout.fragment_biblioteca, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}