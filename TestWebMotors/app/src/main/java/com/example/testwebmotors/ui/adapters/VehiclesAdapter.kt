package com.example.testwebmotors.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testwebmotors.models.Vehicle
import com.example.testwebmotors.databinding.ItemVehicleBinding
import com.example.testwebmotors.ui.VehicleActivity
import com.example.testwebmotors.utils.loadImage
import java.lang.Double.parseDouble
import java.text.DecimalFormat

/**
 * Adapter class to Vehicles
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehiclesAdapter :
    PagingDataAdapter<Vehicle, VehiclesAdapter.VehiclesViewHolder>(VehiclesComparator) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic lateinit var context: Context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehiclesViewHolder {
        context = parent.context

        return VehiclesViewHolder(
            ItemVehicleBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindVehicle(it) }
        holder.itemView.setOnClickListener {
            var intent = Intent(context, VehicleActivity::class.java)
            intent.putExtra("item", item!!)
            startActivity(context, intent, null)
        }
    }

    inner class VehiclesViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindVehicle(item: Vehicle) = with(binding) {
            image.loadImage(item.image.replace("http", "https"))

            val f = DecimalFormat("#,###")
            val price = f.format(parseDouble(item.price.replace(",00", ""))).replace(",", ".")
            textPrice.text = "R\$ $price"

            textMakeModel.text = "${item.make} ${item.model}"
            textDescription.text = item.version
            textYear.text = "${item.yearFab}/${item.yearModel}"

            val km = f.format(parseDouble(item.km.toString())).replace(",", ".")
            textSpeed.text = "$km Km"

            textColor.text = item.color
        }
    }

    object VehiclesComparator : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }
    }
}