package com.axweb.spotifyclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axweb.spotifyclone.R
import com.axweb.spotifyclone.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var categoriaAdapter: CategoriaAdapter

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categorias = arrayListOf<Categoria>()
        categoriaAdapter = CategoriaAdapter(categorias)
        rv_categorias.adapter = categoriaAdapter
        rv_categorias.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyAPI::class.java)
            .listCategorias()
            .enqueue(object : Callback<Categorias>{
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                   Toast.makeText(context,"Erro no servidor",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if (response.isSuccessful ) {
                        response.body()?.let {

                            categoriaAdapter.categorias.clear()
                            categoriaAdapter.categorias.addAll(it.categorias)
                            categoriaAdapter.notifyDataSetChanged()

                        }
                    }
                }
            })
    }

    private inner class CategoriaAdapter(internal val categorias: ArrayList<Categoria>) :
        RecyclerView.Adapter<CategoriaHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
            return CategoriaHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))
        }

        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)
        }

        override fun getItemCount(): Int = categorias.size
    }

    private inner class CategoriaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoria: Categoria) {
            itemView.text_titulo.text = categoria.titulo
            itemView.rv_albuns.adapter = AlbumAdapter(categoria.albuns)
            itemView.rv_albuns.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    //------------------------------------------------------------------------------
    private inner class AlbumAdapter(private val albuns: List<Album>) :
        RecyclerView.Adapter<AlbunsHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AlbunsHolder {

            return AlbunsHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun onBindViewHolder(holder: AlbunsHolder, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }

        override fun getItemCount(): Int = albuns.size
    }

    private inner class AlbunsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
        }
    }
}