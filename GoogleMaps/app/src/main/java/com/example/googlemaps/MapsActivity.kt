package com.example.googlemaps

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.example.googlemaps.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    val binding by lazy { ActivityMapsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)//R.layout.activity_maps
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setContentView(binding.root)
        val fragmentList= listOf(FragmentA(),FragmentB(),FragmentC(),FragmentD())
        val adapter=FragmentAdapter(this)
        adapter.fragmentList=fragmentList
        binding.viewPager.adapter=adapter



    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val swu = LatLng(37.208986445410716, 126.9765191016522)
        //마커 아이콘 만들기
        var bitmapDrawable:BitmapDrawable
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            bitmapDrawable=getDrawable(R.drawable.marker) as BitmapDrawable
        }else{
            bitmapDrawable=resources.getDrawable(R.drawable.marker) as BitmapDrawable
        }
        //마커 크기 변환
        val scaledBitmap=Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 50, 50,false)
        val discriptor = BitmapDescriptorFactory.fromBitmap(scaledBitmap)
        //마커
        val marker = MarkerOptions()
            .position(swu)
            .title("Suwon University")
            .icon(discriptor)

        mMap.addMarker(marker)
        //카메라 위치
        val cameraOption = CameraPosition.Builder()
            .target(swu)
            .zoom(17.0F)
            .build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)
    }

}