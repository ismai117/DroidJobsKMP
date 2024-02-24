package utils.utils



sealed class UIState<T>(
    val data: T? = null,
    val message: String = ""
) {
    class Loading<T>(data: T? = null): UIState<T>(data = data)
    class Success<T>(data: T?) : UIState<T>(data = data)
    class Error<T>(message: String, data: T? = null) : UIState<T>(data = data, message = message)
}
