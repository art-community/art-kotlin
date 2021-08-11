package io.art.rsocket.kotlin

import io.art.communicator.configurator.CommunicatorConfigurator
import io.art.rsocket.module.RsocketActivator.rsocket
import io.art.rsocket.module.RsocketCommunicatorConfigurator
import io.art.rsocket.module.RsocketInitializer
import io.art.rsocket.module.RsocketServerConfigurator
import io.art.server.configurator.ServerConfigurator

fun rsocket(configurator: RsocketInitializer.() -> Any) {
    rsocket { initializer ->
        configurator(initializer)
        initializer
    }
}

fun <T : CommunicatorConfigurator<RsocketCommunicatorConfigurator>> RsocketInitializer.communicator(configurator: RsocketCommunicatorConfigurator.() -> T) = apply {
    communicator { delegate -> configurator(delegate) }
}

fun <T : ServerConfigurator<RsocketServerConfigurator>> RsocketInitializer.server(configurator: RsocketServerConfigurator.() -> T) = apply {
    server { delegate -> configurator(delegate) }
}
