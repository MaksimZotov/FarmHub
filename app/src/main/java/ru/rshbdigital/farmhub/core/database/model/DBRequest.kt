package ru.rshbdigital.farmhub.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.rshbdigital.farmhub.core.database.converter.RequestTypeConverter
import ru.rshbdigital.farmhub.core.model.RequestType

@Entity(tableName = "request_table")
@TypeConverters(RequestTypeConverter::class)
data class DBRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("request_type")
    val requestType: RequestType,
    @ColumnInfo("error_code")
    val errorCode: Int? = null,
    @ColumnInfo("count")
    val count: Int? = null
)