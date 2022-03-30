package io.art.http.kotlin

import io.art.communicator.Communicator
import io.art.http.Http
import io.art.http.module.HttpActivator.http
import io.art.http.module.HttpInitializer
import io.art.http.state.HttpLocalState
import io.art.launcher.Activator
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : Communicator> http(): T = Http.http(T::class.java) as T

inline fun <reified T : Communicator> http(action: T.() -> Any): Any = action(Http.http(T::class.java) as T)

val HttpLocalState.path
    get() = ReadOnlyProperty<Any?, String?> { _, property -> pathParameters()[property.name] }

val HttpLocalState.query
    get() = ReadOnlyProperty<Any?, String?> { _, property -> queryParameters()[property.name] }


fun Activator.http(configurator: HttpInitializer.() -> Any = {}) {
    val activator = http { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
