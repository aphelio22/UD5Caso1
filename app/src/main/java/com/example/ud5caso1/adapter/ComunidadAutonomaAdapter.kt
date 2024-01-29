package com.example.ud5caso1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ud5caso1.ComunidadAutonoma
import com.example.ud5caso1.R
import com.example.ud5caso1.activities.ComunidadDiffUtil

class ComunidadAutonomaAdapter(private var comunidadLista:List<ComunidadAutonoma>, private val onClickListener: (ComunidadAutonoma) -> Unit): RecyclerView.Adapter<ComunidadAutonomaViewHolder>() {
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

    fun updateList(newList:List<ComunidadAutonoma>) {
        val comunidadDiff = ComunidadDiffUtil(comunidadLista, newList)
        val result = DiffUtil.calculateDiff(comunidadDiff)
        comunidadLista = newList
        result.dispatchUpdatesTo(this)
    }

}