package io.art.configurator.kotlin

import io.art.configurator.Configuring.configuration
import io.art.configurator.module.ConfiguratorActivator
import io.art.configurator.module.ConfiguratorInitializer
import io.art.core.constants.StringConstants.EMPTY_STRING
import io.art.launcher.Activator

inline fun <reified T> configuration(): T = configuration<T>(EMPTY_STRING)
inline fun <reified T> configuration(section: String): T = configuration(section, T::class.java) as T

fun Activator.configurator(configurator: ConfiguratorInitializer.() -> Any = {}) {
    val activator = ConfiguratorActivator.configurator { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
