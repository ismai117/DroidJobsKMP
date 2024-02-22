package jobs

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.InputEvent


actual fun onMic() {
    window.alert("Sorry, voice-to-text functionality is currently unavailable.")
}

actual fun onFocused() {
    window.prompt()
}

actual fun onSort() {
    window.alert("Sorting feature is currently being developed.")
}