package com.axweb.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axweb.spotifyclone.R


class BibliotecaFragment : Fragment() {

    companion object {
        fun newInstance() : BibliotecaFragment {
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
}