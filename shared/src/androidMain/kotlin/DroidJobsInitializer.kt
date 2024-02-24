//import android.content.Context
//import androidx.startup.Initializer
//
//internal lateinit var applicationContext: Context
//    private set
//
//public object DroidJobsContext
//
//public class DroidJobsInitializer: Initializer<DroidJobsContext> {
//    override fun create(context: Context): DroidJobsContext {
//        applicationContext = context.applicationContext
//        return DroidJobsContext
//    }
//
//    override fun dependencies(): List<Class<out Initializer<*>>> {
//        return emptyList()
//    }
//}