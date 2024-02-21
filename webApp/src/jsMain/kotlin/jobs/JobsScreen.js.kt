package jobs

import kotlinx.browser.window


actual fun onMic() {
    window.alert("Sorry, voice-to-text functionality is currently unavailable.")
}

actual fun onFocused() {
    window.focus()
}

actual fun onSort() {
    window.alert("Sorting feature is currently being developed.")
}