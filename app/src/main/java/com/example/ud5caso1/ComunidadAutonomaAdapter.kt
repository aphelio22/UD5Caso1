package com.example.ud5caso1

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ComunidadAutonomaAdapter(private val comunidadLista:List<ComunidadAutonoma>, private val onClickListener: (ComunidadAutonoma) -> Unit): RecyclerView.Adapter<ComunidadAutonomaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunidadAutonomaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunidadAutonomaViewHolder(layoutInflater.inflate(R.layout.list_item_comunidad_autonoma, parent, false))
    }

    override fun getItemCount(): Int {
        return comunidadLista.size
    }

    override fun onBindViewHolder(holder: ComunidadAutonomaViewHolder, position: Int) {
        val item = comunidadLista[position]
        holder.render(item, onClickListener)
    }

}