package platform

class WebPlatform : Platform {
    override val type = Platforms.WEB_WASM
}

actual fun getPlatform(): Platform = WebPlatform()