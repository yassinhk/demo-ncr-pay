package tn.com.ncr.ncrpay.domain.model

import com.google.gson.annotations.SerializedName

data class History(
    val cinRecepteur: Int,
    val code: String,
    val createdAt: String,
    val montant: Double,
    val state: String
)
