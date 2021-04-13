package kr.co.brother.carmap

import kr.co.brother.carmap.data.Car
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

class CarOpen {
    companion object{
        val DOMAIN="http://3.34.141.115/dbcon3.php/"
    }
}

interface CarOpenService{
    @GET //("http://3.34.141.115/dbcon3.php") //@GET이랑 @URL 같이 쓸 수 없음
    fun getCar(@Url url:String): Call<Car>
}