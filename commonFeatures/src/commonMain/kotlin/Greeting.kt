import platform.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, platform ${platform.type}!"
    }
}