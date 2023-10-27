package com.example.ud5caso1

import android.app.AlertDialog
import android.telephony.ims.ImsMmTelManager
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.view.View.OnClickListener
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ud5caso1.databinding.ListItemComunidadAutonomaBinding
import com.squareup.picasso.Picasso

class ComunidadAutonomaViewHolder(view: View): ViewHolder(view), View.OnCreateContextMenuListener {
    val binding = ListItemComunidadAutonomaBinding.bind(view)
    private lateinit var comunidad:ComunidadAutonoma
    fun render(item: ComunidadAutonoma, onClickListener: (ComunidadAutonoma) -> Unit) {
        binding.tvNombreComunidad.text = item.nombre
        Picasso.get().load(item.imagen).fit().into(binding.ivBandera)
        itemView.setOnClickListener {
            onClickListener(item)
        }
        comunidad = item
        itemView.setOnCreateContextMenuListener(this)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(comunidad.nombre)
        menu.add(this.adapterPosition, 0, 0, "ELIMINAR")
        menu.add(this.adapterPosition, 1, 1, "EDITAR")
    }
}