package ru.rshbdigital.farmhub.core.util

import okio.IOException
import retrofit2.HttpException

fun Exception.becauseOfBadInternet() = this is IOException

fun Exception.tryGetErrorCode() = (this as? HttpException)?.code()