package tn.com.ncr.ncrpay.data.remote.dto.historyDto

import com.google.gson.annotations.SerializedName
import tn.com.ncr.ncrpay.domain.model.History

data class Data(
    @SerializedName("cin_recepteur")
    val cinRecepteur: Int,
    val code: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("idmandat")
    val idmandat: Int,
    val montant: Double,
    val state: String
)

fun Data.toHistory(): History{
    return History(
        cinRecepteur = cinRecepteur,
        code = code,
        montant = montant,
        createdAt = createdAt,
        state = state
    )
}

