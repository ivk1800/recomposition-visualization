package ru.ivk1800

import io.ktor.http.Url

expect val locationManager: LocationManager

interface LocationManager {
    val href: Url

    fun setSampleId(value: Int?)
}
