package com.example.testwebmotors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testwebmotors.api.VehicleApi
import com.example.testwebmotors.databinding.ActivityMainBinding
import com.example.testwebmotors.ui.adapters.VehiclesAdapter
import com.example.testwebmotors.ui.adapters.VehiclesLoadStateAdapter
import com.example.testwebmotors.ui.view_model.VehiclesViewModel
import com.example.testwebmotors.ui.view_model.VehiclesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Activity Main
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class MainActivity : AppCompatActivity() {

    lateinit var vehiclesViewModel: VehiclesViewModel
    lateinit var vehiclesAdapter: VehiclesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "WebMotors"

        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupViewModel() {
        val factory = VehiclesViewModelFactory(VehicleApi())
        vehiclesViewModel = ViewModelProvider(this, factory).get(VehiclesViewModel::class.java)
    }

    private fun setupList() {
        vehiclesAdapter = VehiclesAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = vehiclesAdapter.withLoadStateHeaderAndFooter(
                header = VehiclesLoadStateAdapter { vehiclesAdapter.retry() },
                footer = VehiclesLoadStateAdapter { vehiclesAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun setupView() {
        lifecycleScope.launch {
            vehiclesViewModel.vehicles.collectLatest { pagedData ->
                vehiclesAdapter.submitData(pagedData)
            }
        }
    }
}