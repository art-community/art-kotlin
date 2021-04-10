package io.art.kotlin.extensions.model.server

import io.art.model.configurator.RsocketServiceModelConfigurator
import io.art.model.configurator.ServerModelConfigurator
import kotlin.reflect.KClass

class ServerModelConfiguratorExtension(val delegate: ServerModelConfigurator) : ServerModelConfigurator() {
    inline fun <reified T> rsocket(noinline configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = rsocket(T::class, configurator)

    inline fun <reified T> rsocket(vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = rsocket(T::class, *configurators)

    fun rsocket(service: Any, configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = rsocket(service::class, configurator)

    fun rsocket(service: Any, vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = rsocket(service::class, *configurators)

    fun rsocket(service: KClass<*>, configurator: RsocketServiceModelConfigurator.() -> Unit = {}) = delegate.rsocket(service.java, { rsocket -> rsocket.apply(configurator) })

    fun rsocket(service: KClass<*>, vararg configurators: RsocketServiceModelConfigurator.() -> Unit) = configurators.forEach { configurator -> rsocket(service, configurator) }
}
