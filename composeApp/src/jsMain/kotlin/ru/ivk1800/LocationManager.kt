package ru.ivk1800

import io.ktor.http.Url
import kotlinx.browser.window
import org.w3c.dom.url.URL

actual val locationManager: LocationManager = object : LocationManager {
    override val href: Url
        get() = Url(window.location.href)

    override fun setSampleId(value: Int?) {
        val url = URL(window.location.toString())
        if (value == null) {
            url.searchParams.delete("sampleId");
        } else {
            url.searchParams.set("sampleId", "$value")
        }
        window.history.replaceState(null, "", url.toString());
    }
}
