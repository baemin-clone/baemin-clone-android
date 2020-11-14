package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.interfaces.ShopDetailVisitFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.models.Result
import kotlinx.android.synthetic.main.fragment_shop_detail_visit.*
import kotlinx.android.synthetic.main.fragment_shop_detail_visit.view.*


class ShopDetailVisitFragment : Fragment(), ShopDetailVisitFragmentView, OnMapReadyCallback {

    val mShopDetailVisitService = ShopDetailVisitService(this)
    var longitude: Double? = 37.359512196163
    var latitude: Double? = 127.105220816584
    var mapView : MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_detail_visit, container, false)

        val shopidx = arguments?.getInt("shopidx") ?: -1

        mShopDetailVisitService.shopDetailVisit(shopidx)
        mapView = view.googlemapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
        return view
    }

    override fun shopDetailVisit(result: Result?) {
        shop_detail_visit_min_price.text = result?.minOrderAmount
        shop_detail_visit_use.text = result?.use
        shop_detail_visit_time.text = result?.cookingTime
        shop_detail_visit_address.text = result?.address
        shop_detail_visit_payment.text = result?.payment
        longitude = result?.longitude
        latitude = result?.latitude
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        MapsInitializer.initialize(this.activity);
        val locate = LatLng(latitude!!, longitude!!)
        val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(
            locate, 14f
        )
        googleMap?.animateCamera(cameraUpdate);

        Log.d("test", "$latitude $longitude")
    }
    override fun onStart() {
        mapView?.onStart()
        super.onStart()
    }

    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView?.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
        super.onLowMemory()
    }


}