package platform

class WebPlatform : Platform {
    override val name: String = "Js"
}

actual fun getPlatform(): Platform = WebPlatform()