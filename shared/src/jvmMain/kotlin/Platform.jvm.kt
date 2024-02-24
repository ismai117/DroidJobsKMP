class JVMPlatform: Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = JVMPlatform()