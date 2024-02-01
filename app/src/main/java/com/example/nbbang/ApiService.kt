package com.example.nbbang

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

// ApiService.kt
interface ApiService {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    /*@FormUrlEncoded
    @POST("/perform_login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):String
    //: Response<LoginResponse>*/

    @GET("/perform_logout")
    suspend fun logout(): Response<Void>



    /*@GET("/users")
    fun getUsers(): Call<List<User>>*/

    /*@POST("/signup")
    fun signup(@Body signupRequest: SignupRequest): Call<SignupResponse>
    // 다른 API 엔드포인트들도 필요에 따라 추가하세요.*/

    @POST("/user")
    fun signup(@Body signupRequest: SignupRequest): Call<SignupResponse>

    // LoginRequest.kt
    data class LoginRequest(val username: String, val password: String)

    // SignupRequest.kt
    data class SignupRequest(val email: String, val password: String, val username: String)

    data class User(val id: Int, val email: String, val password: String, val username: String)

    data class LoginResponse(val token: String, val user: User)

    data class SignupUser(val id: Int, val email: String, val password: String, val username: String)

    data class SignupResponse(val message: String, val user: SignupUser)

    @GET("/api/data")
    fun getData(): Call<LoginResponse>

    @GET("/api/room/1")
    fun getRoom(): Call<RoomResponse>

    data class RoomResponse(val id: Long, val info: String, val show: Boolean, val title: String, val leader: Long, val code:String)

    @GET("/api/rooms")
    fun getRooms(): Call<List<RoomResponse>>

}