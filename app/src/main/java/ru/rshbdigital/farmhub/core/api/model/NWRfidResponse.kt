package ru.rshbdigital.farmhub.core.api.model

import com.google.gson.annotations.SerializedName

data class NWRfidResponse(
    @SerializedName("Token") val token: String? = null,
)
