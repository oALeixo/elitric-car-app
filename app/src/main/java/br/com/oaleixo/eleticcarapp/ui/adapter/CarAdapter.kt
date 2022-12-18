package br.com.oaleixo.eleticcarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.oaleixo.eleticcarapp.R
import br.com.oaleixo.eleticcarapp.domain.Carro

class  CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen : Boolean = false) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>(){

    var carItemLister: (Carro) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }
    // Pega o conteudo da view e troca pela informação de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.bateria.text = carros[position].bateria
        holder.potencia.text = carros[position].potencia
        holder.recarga.text = carros[position].recarga
        if (isFavoriteScreen){
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        }
        holder.favorite.setOnClickListener {
            val carro = carros[position]
            carItemLister(carro)
            setupFavorite(carro, holder)
        }
    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {

            carro.isFavorite = !carro.isFavorite

            if (carro.isFavorite)
                holder.favorite.setImageResource(R.drawable.ic_star_selected)
            else
                holder.favorite.setImageResource(R.drawable.ic_star)

    }

    // Pega a quantidade de carros da lista
    override fun getItemCount(): Int = carros.size

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val preco : TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        val favorite: ImageView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
                favorite = findViewById(R.id.iv_favorite)
            }

        }
    }
}



