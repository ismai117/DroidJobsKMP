class WebPlatform : Platform {
    override val name: String = "Wasm"
}

actual fun getPlatform(): Platform = WebPlatform()