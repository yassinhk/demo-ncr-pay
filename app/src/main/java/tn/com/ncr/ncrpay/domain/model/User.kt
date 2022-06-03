package tn.com.ncr.ncrpay.domain.model


data class User(
    val cin: Int,
    val createdAt: String?="",
    val email: String,
    val idClient: Int,
    val lastName: String,
    val name: String,
    val phone: String,
    val username: String
)
