package platform

import kotlinx.coroutines.Dispatchers

class AndroidPlatform : Platform {
    override val type = Platforms.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()