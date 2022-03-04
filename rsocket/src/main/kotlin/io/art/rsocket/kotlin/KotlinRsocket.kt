package io.art.rsocket.kotlin

import io.art.communicator.model.ConnectorIdentifier
import io.art.launcher.Activator
import io.art.rsocket.Rsocket.rsocket
import io.art.rsocket.module.RsocketActivator
import io.art.rsocket.module.RsocketInitializer

inline fun <reified T : ConnectorIdentifier> rsocket(): T = rsocket(T::class.java) as T

inline fun <reified T : ConnectorIdentifier> rsocket(action: T.() -> Any): Any = action(rsocket(T::class.java) as T)



fun Activator.rsocket(configurator: RsocketInitializer.() -> Any = {}) {
    val activator = RsocketActivator.rsocket { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
