package kr.co.brother.activity

import android.content.Intent
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.brother.activity.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) } //build grading 파일에 추가 필요
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //변수 잘 설정해야함 처음 값 바꾸는거임
        val intent= Intent(this, SubActivity::class.java)  //subActivity 잘보기
        intent.putExtra("from1","Hello Bundle")
        intent.putExtra("from2",2021)
        binding.btnStart.setOnClickListener { startActivityForResult(intent,99) } //btnStart가 xml파일에서 버튼 id값

        val Mapintent=Intent(this,MapsActivity::class.java)
        binding.maps.setOnClickListener { startActivity(Mapintent) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== RESULT_OK){
            when(requestCode) {
                99-> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
