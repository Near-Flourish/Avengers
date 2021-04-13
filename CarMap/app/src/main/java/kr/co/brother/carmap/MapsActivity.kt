package kr.co.brother.carmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.brother.carmap.data.Car
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        loadCar()
    }
    fun loadCar() {
        val retrofit = Retrofit.Builder()
            .baseUrl(CarOpen.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val CarOpenService = retrofit.create(CarOpenService::class.java)

        CarOpenService
            .getCar(CarOpen.DOMAIN)
            .enqueue(object : Callback<Car> {
                override fun onResponse(call: Call<Car>, response: Response<Car>) {
                    ShowCar(response.body() as Car)
                }

                override fun onFailure(call: Call<Car>, t: Throwable) {
                    Toast.makeText(baseContext,"데이터 가져올 수 없음",Toast.LENGTH_LONG).show()
                }

            })
    }
    fun ShowCar(Cars:Car){
        val latLngBounds= LatLngBounds.Builder()

        for(car in Cars.ddu){
            val position=LatLng(car.latitude.toDouble(),car.longitude.toDouble())
            val marker=MarkerOptions().position(position).title(car.Name)
            mMap.addMarker(marker)
           // var obj=mMap.addMarker(marker)


            latLngBounds.include(marker.position)
        }

        val bounds=latLngBounds.build()
        val padding=0
        val updated=CameraUpdateFactory.newLatLngBounds(bounds,padding)
        mMap.moveCamera(updated)
    }
}