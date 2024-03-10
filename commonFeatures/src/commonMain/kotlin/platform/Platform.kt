package platform

enum class Platforms {
    ANDROID,
    IOS,
    DESKTOP,
    WEB_JS,
    WEB_WASM
}

interface Platform {
    val type: Platforms
}

expect fun getPlatform(): Platform