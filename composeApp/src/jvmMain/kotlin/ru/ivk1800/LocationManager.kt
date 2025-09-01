package ru.ivk1800

import io.ktor.http.Url

actual val locationManager: LocationManager = object : LocationManager {
    override val href: Url
        get() = Url("http://localhost:8080/")

    override fun setSampleId(value: Int?) = Unit
}
