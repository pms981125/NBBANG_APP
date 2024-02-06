package com.example.nbbang

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nbbang.ApiClient.apiService
import com.example.nbbang.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// LoginActivity.kt
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val INTERNET_PERMISSION_CODE = 123

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternetPermission()

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextID.text.toString()
            val password = binding.editTextPassword.text.toString()

            loginUser(email, password)

            /*CoroutineScope(Dispatchers.IO).launch {
                //try {
                val response = apiService.login("user@example.com", "password")
                println(response + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%")

                    if (response.isSuccessful) {
                        // 로그인 성공
                        val responseData = response.body()
                        // 추가 작업 수행...
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        //intent.putExtra("user", responseData)
                        startActivity(intent)
                    } else {
                        // 로그인 실패fhrm
                        // 에러 처리...
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }*/


        }

        binding.buttonSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }


    private fun loginUser(email: String, password: String) {

        /*val newRequest = ApiService.LoginRequest(email, password).toString()
        newRequest.replace("(","{")
        newRequest.replace(")", "}")*/

        var json: String = ApiService.LoginRequest(email, password).toString().replace("(", "{")
        json = json.replace(")", "}")
        //json = json.replace("이름tDto", "")
        json = json.replace("email=", "\"email\":")
        json = json.replace("password=", "\"password\":")
        //json = json.replace("devId=", "\"devId\":")

        val loginRequest = json
        //ApiService.LoginRequest(username, password).toString().replace("(","{").replace(")", "}")//?
        /*var json = loginRequest.toString().replace("(","{")
        json = json.replace(")", "}");
        json = json.replace("이름tDto", "");
        json = json.replace("parentId=", "\"parentId\":");
        json = json.replace("childId=", "\"childId\":");
        json = json.replace("devId=", "\"devId\":");*/


        apiService.getData().enqueue(object : Callback<ApiService.LoginResponse> {
            override fun onResponse(call: Call<ApiService.LoginResponse>, response: Response<ApiService.LoginResponse>) {
                if (response.isSuccessful) {
                    /*val token = response.body()?.token
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("user", token)*/
                    val id = response.body()?.id
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("user", id)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ApiService.LoginResponse>, t: Throwable) {
                // 통신 실패 처리
                Toast.makeText(this@LoginActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })

        /*ApiClient.apiService.login(loginRequest).enqueue(object : Callback<ApiService.LoginResponse> {
            override fun onResponse(call: Call<ApiService.LoginResponse>, response: Response<ApiService.LoginResponse>) {
                if (response.isSuccessful) {
                    // 로그인 성공
                    val token = response.body()?.token
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("user", token)
                    startActivity(intent)
                    // 토큰을 사용하여 세션 유지 등의 작업을 수행할 수 있습니다.
                } else {
                    // 로그인 실패
                    // 에러 처리
                    Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ApiService.LoginResponse>, t: Throwable) {
                // 통신 실패
                // 에러 처리
                Toast.makeText(this@LoginActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })*/
    }

    // 권한을 확인하고 없으면 요청하는 함수
    private fun checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 없으면 사용자에게 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.INTERNET),
                INTERNET_PERMISSION_CODE
            )
        } else {
            // 이미 권한이 있는 경우 처리할 내용
            // 예: 특정 기능 실행
            performInternetTask()
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            INTERNET_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 허용된 경우 처리할 내용
                    // 예: 특정 기능 실행
                    performInternetTask()
                } else {
                    // 권한이 거부된 경우 사용자에게 안내 또는 다른 대응
                    Toast.makeText(this, "인터넷 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 인터넷 권한이 허용된 경우 실행할 작업
    private fun performInternetTask() {
        // 권한이 허용되었을 때 실행할 작업 수행
        Toast.makeText(this, "인터넷 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
    }
}
