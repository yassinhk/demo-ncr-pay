package tn.com.ncr.ncrpay.data.remote.dto.userDto

import tn.com.ncr.ncrpay.domain.model.User

data class UserDto(
    val `data`: Data,
    val success: Boolean
)

fun UserDto.toUser() : User {
    return User(
        username = data.username,
        phone = data.phone,
        createdAt = data.createdAt,
        name = data.name,
        email = data.email,
        idClient = data.idClient,
        cin = data.cin,
        lastName = data.lastName
    )
}
