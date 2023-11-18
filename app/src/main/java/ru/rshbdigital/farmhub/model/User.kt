package ru.rshbdigital.farmhub.model

data class User(
    val id: String,
    val rfid: String?,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val employeeId: String?,
    val role: Role,
    val phone: String?,
) {
    enum class Role {
        UNKNOWN,
        AGRICULTURIST,
        MECHANIZER,
        DISPATCHER,
    }
}
