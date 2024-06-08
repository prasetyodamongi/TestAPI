package com.pcoding.testapi.data.api

import com.pcoding.testapi.data.model.AddMemberModel
import com.pcoding.testapi.data.model.MemberModel
import com.pcoding.testapi.data.model.PostModel
import com.pcoding.testapi.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: token 123", "Content-Type: Application/json")
    @POST("Walet/post/1/")
    suspend fun postPesan(@Body pesan: PostModel): Response<Any>

    @GET("Walet/user/")
    suspend fun getData(): List<UserModel>

    @GET("Walet/member/")
    suspend fun getAllMembers(): Array<MemberModel>

    @GET("Walet/member/{id}")
    suspend fun getMemberById(@Path("id") id: String): List<MemberModel>

    @Headers("Authorization: token 123", "Content-Type: Application/json")
    @POST("Walet/addmember/")
    suspend fun addMember(@Body addMember: AddMemberModel): Response<Any>

}