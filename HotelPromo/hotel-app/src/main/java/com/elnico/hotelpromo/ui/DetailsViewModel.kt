package com.elnico.hotelpromo.ui

import android.app.Application
import android.graphics.Canvas
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.osmdroid.tileprovider.MapTileProviderBasic
import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.DefaultOverlayManager
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.TilesOverlay

class DetailsViewModel(private val application: Application): AndroidViewModel(application) {

    private var mapView: MapView? = null

    val updateMapFlow = MutableStateFlow(true)

    fun updateMapIfNeeded(mapView: MapView) {
        this.mapView = mapView

        if (updateMapFlow.value) {
            val tileProvider = MapTileProviderBasic(application)
            val tileSource: ITileSource = XYTileSource(
                "hot",
                0,
                20,
                18,
                ".png",
                arrayOf("https://a.tile.openstreetmap.fr/hot/", "https://b.tile.openstreetmap.fr/hot/")
            )
            tileProvider.tileSource = tileSource
            val tilesOverlay = TilesOverlay(tileProvider, application)

            mapView.overlays.add(tilesOverlay)
            mapView.overlayManager = CustomOverlayManager(tilesOverlay)

            mapView.setMultiTouchControls(true)
            mapView.setBuiltInZoomControls(false)

            //appendLocationMarkers(mapView)

            val startMarker = Marker(mapView)
            startMarker.position = GeoPoint(43.2567, 76.9286)
            //startMarker.icon = createMarkerIcon()
            //startMarker.image = createMarkerIcon()
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            mapView.overlays.add(startMarker)

            mapView.controller.animateTo(GeoPoint(43.2567, 76.9286))
            //mapView.controller.zoomTo(5.0)
            mapView.controller.zoomTo(10.0)

            updateMapFlow.value = false
        }
    }

    private class CustomOverlayManager(tilesOverlay: TilesOverlay?) :
        DefaultOverlayManager(tilesOverlay) {
        override fun onDraw(c: Canvas, pMapView: MapView) {
            super.onDraw(c, pMapView)
            pMapView.invalidate()

            //potential fix for #52 pMapView.invalidate();
        }
    }
}