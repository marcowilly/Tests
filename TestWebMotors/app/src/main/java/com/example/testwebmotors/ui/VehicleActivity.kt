package com.example.testwebmotors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testwebmotors.databinding.ActivityVehicleBinding
import com.example.testwebmotors.models.Vehicle
import com.example.testwebmotors.utils.loadImage
import java.lang.Double.parseDouble
import java.text.DecimalFormat

/**
 * Activity Vehicle
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle:Bundle = intent.extras!!
        val item :Vehicle = bundle.get("item") as Vehicle
        addItemView(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun addItemView(item: Vehicle){
        binding.toolbar.title = "${item.make} ${item.model}"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.image.loadImage(item.image.replace("http", "https"))
        binding.textMake.text = "Marca ${item.make}"

        val f = DecimalFormat("#,###")
        val price = f.format(parseDouble(item.price.replace(",00", ""))).replace(",", ".")
        binding.textPrice.text = "R\$ $price"

        binding.textMakeModel.text = "${item.make} ${item.model}"
        binding.textDescription.text = item.version
        binding.textYearFab.text = "Fabricado em ${item.yearFab}"
        binding.textYearModel.text = "Lançado em ${item.yearModel}"

        val km = f.format(parseDouble(item.km.toString())).replace(",", ".")
        binding.textSpeed.text = "$km Km"

        binding.textColor.text = item.color
    }
}