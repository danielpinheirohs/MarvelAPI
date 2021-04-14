package com.pinheirosapplications.marvelapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pinheirosapplications.marvelapi.R
import com.pinheirosapplications.marvelapi.models.Hero

class HerosAdapter(
    val activity: FragmentActivity
) : RecyclerView.Adapter<HerosAdapter.HerosViewHolder>() {

    private var herosList: List<Hero> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): HerosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        return HerosViewHolder(itemView, activity)
    }

    override fun getItemCount() = herosList.count()

    fun submitList(herosList: List<Hero>) {
        this.herosList = herosList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: HerosViewHolder, position: Int) {
        viewHolder.bindView(herosList[position])
    }

    class HerosViewHolder(view: View, private var activity: FragmentActivity) :
        RecyclerView.ViewHolder(view){
        private var foto = itemView.findViewById<ImageView>(R.id.hero_image)
        private var nome = itemView.findViewById<TextView>(R.id.hero_nome)
        private var id = itemView.findViewById<TextView>(R.id.hero_id)
        private var data = itemView.findViewById<TextView>(R.id.hero_data)


        fun bindView(hero: Hero) {

            nome.text = hero.name
            id.text = hero.id.toString()
            data.text = hero.modified

            var urlImagem = hero.thumbnail.path + "." + hero.thumbnail.extension

            if(!urlImagem.contains("https")){
                urlImagem = urlImagem.substringAfter("http")
                urlImagem = "https" + urlImagem
            }

            foto.load(urlImagem){
                placeholder(R.drawable.image_not_found)
                error(R.drawable.image_not_found)
            }

            itemView.setOnClickListener {
               // activity.findNavController().navigate(R.id.recepcaoFragment)
            }
        }
    }
}