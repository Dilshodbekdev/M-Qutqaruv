package com.technocorp.mqutqaruv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.technocorp.mqutqaruv.databinding.FragmentMapBinding
import com.technocorp.mqutqaruv.util.BindingFragment

class MapFragment : BindingFragment<FragmentMapBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMapBinding::inflate

    var mapView: MapView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.mapView
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}