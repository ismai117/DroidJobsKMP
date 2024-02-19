package utils


import kotlinx.browser.window


internal fun openUrl(url: String?) {
    url?.let { window.open(it) }
}