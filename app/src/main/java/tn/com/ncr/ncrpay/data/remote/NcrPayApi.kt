package tn.com.ncr.ncrpay.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.AccountDto
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatDto
import tn.com.ncr.ncrpay.data.remote.dto.accountDto.SendMandatRequest
import tn.com.ncr.ncrpay.data.remote.dto.historyDto.HistoryDto
import tn.com.ncr.ncrpay.data.remote.dto.userDto.RequestBody
import tn.com.ncr.ncrpay.data.remote.dto.userDto.UserDto

interface NcrPayApi {

      @POST("v1/users/auth")
      suspend fun auth(@Body requestBody: RequestBody): UserDto

      @GET("v1/account/{iduser}")
      suspend fun getAccount(@Path("iduser") clientId : Int) : AccountDto

      @POST("v1/account/send")
      suspend fun sendMandat(@Body sendMandatRequest: SendMandatRequest) : SendMandatDto

      @GET("v1/account/history/{idaccount}")
      suspend fun getHistory(@Path("idaccount") accountId : Int) : HistoryDto

}
