package io.art.rsocket.kotlin

import io.art.communicator.Portal
import io.art.launcher.Activator
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaMethod
import io.art.rsocket.Rsocket.rsocket
import io.art.rsocket.Rsocket.rsocketState
import io.art.rsocket.module.RsocketActivator
import io.art.rsocket.module.RsocketInitializer
import io.art.rsocket.state.RsocketModuleState.RsocketLocalState

inline fun <reified T : Portal> rsocket(): T = rsocket(T::class.java) as T

inline fun <reified T : Portal> rsocket(action: T.() -> Any): Any = action(rsocket(T::class.java) as T)


inline fun <reified C, reified M : MetaClass<C>> rsocketState(crossinline method: (owner: M) -> MetaMethod<*>): RsocketLocalState = rsocketState(C::class.java) { owner: M ->
    method(owner)
}

inline fun <reified C, reified M : MetaClass<C>> rsocketState(crossinline method: (owner: M) -> MetaMethod<*>, action: RsocketLocalState.() -> Any) {
    action(rsocketState(C::class.java) { owner: M -> method(owner) })
}


fun Activator.rsocket(configurator: RsocketInitializer.() -> Any) {
    val activator = RsocketActivator.rsocket { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
