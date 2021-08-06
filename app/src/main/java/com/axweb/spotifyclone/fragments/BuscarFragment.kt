package com.axweb.spotifyclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axweb.spotifyclone.R
import com.axweb.spotifyclone.model.Secao
import com.axweb.spotifyclone.model.dados
import kotlinx.android.synthetic.main.fragment_buscar.*
import kotlinx.android.synthetic.main.secao_item.view.*


class BuscarFragment : Fragment() {

    private lateinit var secaoAdapter: SecaoAdapter

    companion object {
        fun newInstance(): BuscarFragment {
            val fragmentBuscar = BuscarFragment()
            val argumentos = Bundle()
            fragmentBuscar.arguments = argumentos
            return fragmentBuscar

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        secaoAdapter = SecaoAdapter(dados())
        rv_secao.adapter = secaoAdapter
        rv_secao.layoutManager = GridLayoutManager(context, 2)
    }

    private inner class SecaoAdapter(private val secoes: MutableList<Secao>) :
        RecyclerView.Adapter<SecaoAdapter.SecaoHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecaoHolder {
            return SecaoHolder(layoutInflater.inflate(R.layout.secao_item, parent, false))
        }

        override fun onBindViewHolder(holder: SecaoHolder, position: Int) {
            val secao: Secao = secoes[position]
            holder.bind(secao)
        }

        override fun getItemCount(): Int = secoes.size

        private inner class SecaoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(secao: Secao) {
                itemView.imagem_secao.setImageResource(secao.secao)
                itemView.nomeSecao.text = secao.nomeSecao
            }

        }
    }
}