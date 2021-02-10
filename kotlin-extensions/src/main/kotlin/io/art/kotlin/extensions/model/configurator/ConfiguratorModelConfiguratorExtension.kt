package io.art.kotlin.extensions.model.configurator

import io.art.model.configurator.ConfiguratorModelConfigurator
import kotlin.reflect.KClass

class ConfiguratorModelConfiguratorExtension(val delegate: ConfiguratorModelConfigurator) : ConfiguratorModelConfigurator() {
    inline fun <reified T> configuration() = configuration(T::class)

    fun configuration(vararg types: Any) = types.forEach { type -> configuration(type::class) }

    fun configuration(vararg types: KClass<*>) = types.forEach { type -> delegate.configuration(type.java) }

    inline fun <reified T> configuration(section: String) = configuration(section, T::class)

    fun configuration(section: String, type: Any) = configuration(section, type::class)

    fun configuration(section: String, type: KClass<*>) = delegate.configuration(section, type.java)
}
