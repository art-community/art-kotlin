package io.art.logging.kotlin

import io.art.launcher.Activator
import io.art.logging.Logging.logger
import io.art.logging.logger.Logger
import io.art.logging.module.LoggingActivator
import io.art.logging.module.LoggingInitializer
import kotlin.reflect.KClass

fun logger(action: Logger.() -> Any) {
    action(logger())
}


fun logger(type: KClass<*>): Logger = logger(type::class.java)
fun logger(name: String, action: Logger.() -> Any) {
    action(logger(name))
}

fun logger(type: KClass<*>, action: Logger.() -> Any) {
    logger(type::class.java, action)
}

fun logger(type: Class<*>, action: Logger.() -> Any) {
    action(logger(type))
}


fun trace(message: String) = logger().trace(message)
fun trace(format: String, vararg arguments: Any) = logger().trace(format, arguments)
fun trace(message: String, error: Throwable) = logger().trace(message, error)


fun debug(message: String) = logger().debug(message)
fun debug(format: String, vararg arguments: Any) = logger().debug(format, arguments)
fun debug(message: String, error: Throwable) = logger().debug(message, error)


fun info(message: String) = logger().info(message)
fun info(format: String, vararg arguments: Any) = logger().info(format, arguments)
fun info(message: String, error: Throwable) = logger().info(message, error)


fun warn(message: String) = logger().warn(message)
fun warn(format: String, vararg arguments: Any) = logger().warn(format, arguments)
fun warn(message: String, error: Throwable) = logger().warn(message, error)


fun error(message: String) = logger().error(message)
fun error(format: String, vararg arguments: Any) = logger().error(format, arguments)
fun error(message: String, error: Throwable) = logger().error(message, error)

inline fun <reified T : Any> T.info(): T = apply { logger(T::class.java).info(toString()) }
inline fun <reified T : Any> T.info(message: String): T = apply { logger(T::class.java).info(message) }
inline fun <reified T : Any> T.info(format: String, vararg arguments: Any): T = apply { logger(T::class.java).info(format, arguments) }
inline fun <reified T : Any> T.info(message: String, error: Throwable): T = apply { logger(T::class.java).info(message, error) }

inline fun <reified T : Any> T.error(): T = apply { logger(T::class.java).error(toString()) }
inline fun <reified T : Any> T.error(message: String): T = apply { logger(T::class.java).error(message) }
inline fun <reified T : Any> T.error(format: String, vararg arguments: Any): T = apply { logger(T::class.java).error(format, arguments) }
inline fun <reified T : Any> T.error(message: String, error: Throwable): T = apply { logger(T::class.java).error(message, error) }

fun Activator.logging(configurator: LoggingInitializer.() -> Any = {}) {
    val activator = LoggingActivator.logging { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
