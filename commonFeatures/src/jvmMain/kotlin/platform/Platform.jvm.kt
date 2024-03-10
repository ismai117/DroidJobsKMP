package platform

class JVMPlatform: Platform {
    override val type = Platforms.DESKTOP
}

actual fun getPlatform(): Platform = JVMPlatform()