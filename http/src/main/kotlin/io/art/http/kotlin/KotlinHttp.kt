package io.art.http.kotlin

import io.art.communicator.ConnectorIdentifier
import io.art.http.Http.http
import io.art.http.module.HttpActivator.http
import io.art.http.module.HttpInitializer
import io.art.http.state.HttpLocalState
import io.art.launcher.Activator
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : ConnectorIdentifier> http(): T = http(T::class.java) as T

inline fun <reified T : ConnectorIdentifier> http(action: T.() -> Any): Any = action(http(T::class.java) as T)

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
