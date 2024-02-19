class WasmPlatform: Platform {
    override val name: String = "Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()