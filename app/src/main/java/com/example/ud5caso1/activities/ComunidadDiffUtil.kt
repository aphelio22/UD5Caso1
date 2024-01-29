package com.example.ud5caso1.activities

import androidx.recyclerview.widget.DiffUtil
import com.example.ud5caso1.ComunidadAutonoma

class ComunidadDiffUtil(
    private val oldList: List<ComunidadAutonoma>,
    private val newList: List<ComunidadAutonoma>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}