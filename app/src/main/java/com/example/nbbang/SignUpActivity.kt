package com.example.nbbang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nbbang.ApiService.SignupRequest
import com.example.nbbang.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// SignupActivity.kt
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val username = binding.editTextName.text.toString()

            signupUser(email, password, username)
        }
    }
    private fun signupUser(email: String, password: String, username: String) {
        val signupRequest = SignupRequest(email, password, username)

        ApiClient.apiService.signup(signupRequest).enqueue(object : Callback<ApiService.SignupResponse> {
            override fun onResponse(call: Call<ApiService.SignupResponse>, response: Response<ApiService.SignupResponse>) {
                if (response.isSuccessful) {
                    val signupUser = response.body()?.user
                    // 회원 가입 성공
                    // 사용자 정보를 활용하여 로직 수행
                    Toast.makeText(this@SignupActivity, "회원 가입 성공", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    // 회원 가입 실패
                    // 에러 처리
                    Toast.makeText(this@SignupActivity, "회원 가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiService.SignupResponse>, t: Throwable) {
                // 통신 실패
                // 에러 처리
                Toast.makeText(this@SignupActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}
