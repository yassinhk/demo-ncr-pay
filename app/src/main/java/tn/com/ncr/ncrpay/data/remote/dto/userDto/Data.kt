package tn.com.ncr.ncrpay.data.remote.dto.userDto

import com.google.gson.annotations.SerializedName

data class Data(
    val cin: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val email: String,
    @SerializedName("idclient")
    val idClient: Int,
    @SerializedName("last_name")
    val lastName: String,
    val name: String,
    val password: String,
    val phone: String,
    val username: String
)
