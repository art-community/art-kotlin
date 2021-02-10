package io.art.kotlin.extensions.model.server

import io.art.model.configurator.ServerModelConfigurator
import kotlin.reflect.KClass

class ServerModelConfiguratorExtension(val delegate: ServerModelConfigurator) {
    fun rsocket(service: Any) = rsocket(service::class)

    fun rsocket(service: KClass<*>) = delegate.rsocket(service.java).let { this }
}
