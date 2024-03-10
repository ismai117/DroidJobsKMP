package platform

class WebPlatform : Platform {
    override val type = Platforms.WEB_JS
}

actual fun getPlatform(): Platform = WebPlatform()