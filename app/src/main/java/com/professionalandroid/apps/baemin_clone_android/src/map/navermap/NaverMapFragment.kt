package com.professionalandroid.apps.baemin_clone_android.src.map.navermap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.professionalandroid.apps.baemin_clone_android.NowAddress
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.GpsTracker
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.AdditionalAddressFragment
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.interfaces.NaverMapFragmentView
import kotlinx.android.synthetic.main.fragment_naver_map.*
import kotlinx.android.synthetic.main.fragment_naver_map.view.*

class NaverMapFragment : Fragment(), NaverMapFragmentView, OnMapReadyCallback {

    var isAddr = true   // addr or roadaddr
    var addr = arrayOf("", "")
    var naverMap: NaverMap? = null
    val navermapService = NavermapServiece(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.d("test", "navermap_resume")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_naver_map, container, false)

        val marker1 = Marker()
        setMarker(marker1, 33.2712, 126.5354, R.drawable.marker, 0);


        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.naver_map, it).commit()
            }
        mapFragment.getMapAsync(this)

        view.naver_map_change.setOnClickListener {
            isAddr = !isAddr
            if(isAddr){
                view.naver_map_change.text = "도로명 주소로 보기"
                view.naver_map_address.text = addr[0]
                Log.d("test", isAddr.toString())
            }
            else{
                view.naver_map_change.text = "지번 주소로 보기"
                view.naver_map_address.text = addr[1]
            }
        }

        return view
    }

    private fun setMarker(
        marker: Marker,
        lat: Double,
        lng: Double,
        resourceID: Int,
        zIndex: Int
    ) {
        //원근감 표시
        marker.isIconPerspectiveEnabled = true
        //아이콘 지정
        marker.icon = OverlayImage.fromResource(resourceID)
        //마커의 투명도
        marker.alpha = 0.8f
        //마커 위치
        marker.position = LatLng(lat, lng)
        //마커 우선순위
        marker.zIndex = zIndex
        //마커 표시
        marker.map = naverMap
    }


    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        this.naverMap?.mapType = NaverMap.MapType.Basic

        val now_location = GpsTracker(context!!)
        val latitude = now_location.latitude
        val longitude = now_location.longitude

        Log.d("test", longitude.toString() + latitude.toString())

        // first camera location
        val cameraPosition = CameraPosition(
            LatLng(latitude, longitude),  // 위치 지정
            9.0 // 줌 레벨
        )
        this.naverMap?.cameraPosition = cameraPosition

        this.naverMap?.addOnCameraChangeListener { reason, animated ->
            naver_map_submit_btn.setBackgroundResource(R.drawable.round_button)
        }

        this.naverMap?.addOnCameraIdleListener {
            val coords = naverMap.cameraPosition.target.longitude.toString() + "," + naverMap.cameraPosition.target.latitude.toString()
            naver_map_guide.visibility = View.GONE
            navermapService.reverse_geocoding("6otyicnqxq", "aaruBYvhXsERQq3YDxMDY7gZWQcWN9ehdwzkRmG8", coords, "json", "addr,roadaddr")
        }
    }

    override fun saveNowPlace(addr0: String, addr1: String) {
        addr[0] = addr0
        addr[1] = addr1

        if(addr[0] == ""){  // addr and roadaddr are empty like sea
            naver_map_address.text = "위치 이동중"
            naver_map_change.visibility = View.GONE
            naver_map_submit_btn.setBackgroundResource(R.drawable.round_button)
            naver_map_submit_btn.setOnClickListener {  }
        }
        else if (addr[1] == ""){   // addr exist but roadaddr is empty
            isAddr = true
            naver_map_change.visibility = View.GONE
            naver_map_address.text = addr[0]
            naver_map_submit_btn.setBackgroundResource(R.drawable.round_button2)
            naver_map_submit_btn.setOnClickListener {
                val additionalAddressPage = AdditionalAddressFragment().apply {
                    arguments = Bundle().apply {
                        putString("roadaddr", addr[1])
                        putString("addr", addr[0])
                        putDouble("longitude", naverMap?.cameraPosition?.target?.longitude!!)
                        putDouble("latitude", naverMap?.cameraPosition?.target?.latitude!!)
                        putBoolean("isAddr", isAddr)
                    }
                }
                naver_map_submit_btn.visibility = View.GONE
                (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.naver_map_fragment, additionalAddressPage).commit()
            }
        }
        else if(addr[0] != "" && addr[1] != ""){    // addr and roadaddr exist
            naver_map_change.visibility = View.VISIBLE
            if (isAddr) {
                naver_map_address.text = addr[0]
            } else {
                naver_map_address.text = addr[1]
            }
            naver_map_submit_btn.setBackgroundResource(R.drawable.round_button2)
            naver_map_submit_btn.setOnClickListener {
                val additionalAddressPage = AdditionalAddressFragment().apply {
                    arguments = Bundle().apply {
                        putString("roadaddr", addr[1])
                        putString("addr", addr[0])
                        putDouble("longitude", naverMap?.cameraPosition?.target?.longitude!!)
                        putDouble("latitude", naverMap?.cameraPosition?.target?.latitude!!)
                        putBoolean("isAddr", isAddr)
                    }
                }
                naver_map_submit_btn.visibility = View.GONE
                (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.naver_map_fragment, additionalAddressPage).commit()
            }
        }
    }

}