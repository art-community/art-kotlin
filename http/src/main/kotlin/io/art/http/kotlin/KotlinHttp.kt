package io.art.http.kotlin

import io.art.communicator.Portal
import io.art.http.Http.*
import io.art.http.module.HttpActivator.http
import io.art.http.module.HttpInitializer
import io.art.http.state.HttpLocalState
import io.art.http.state.WsLocalState
import io.art.launcher.Activator
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaMethod
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : Portal> http(): T = http(T::class.java) as T

inline fun <reified T : Portal> http(action: T.() -> Any): Any = action(http(T::class.java) as T)


inline fun <reified C, reified M : MetaClass<C>> httpState(crossinline method: (owner: M) -> MetaMethod<*>): HttpLocalState = httpState(C::class.java) { owner: M -> method(owner) }

inline fun <reified C, reified M : MetaClass<C>, T> httpState(crossinline method: (owner: M) -> MetaMethod<*>, action: HttpLocalState.() -> T) =
        action(httpState(C::class.java) { owner: M -> method(owner) })


inline fun <reified C, reified M : MetaClass<C>> wsState(crossinline method: (owner: M) -> MetaMethod<*>): WsLocalState = wsState(C::class.java) { owner: M -> method(owner) }

inline fun <reified C, reified M : MetaClass<C>, T> wsState(crossinline method: (owner: M) -> MetaMethod<*>, action: WsLocalState.() -> T) =
        action(wsState(C::class.java) { owner: M -> method(owner) })

val HttpLocalState.path
    get() = ReadOnlyProperty<Any?, String?> { _, property -> pathParameters()[property.name] }

val HttpLocalState.query
    get() = ReadOnlyProperty<Any?, String?> { _, property -> queryParameters()[property.name] }


fun Activator.http(configurator: HttpInitializer.() -> Any) {
    val activator = http { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
