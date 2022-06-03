package tn.com.ncr.ncrpay.data.remote.dto.historyDto

data class HistoryDto(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)
