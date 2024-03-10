package platform

class IOSPlatform: Platform {
    override val type = Platforms.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()