package io.art.kotlin.extensions.model.configurator

import io.art.model.configurator.ConfiguratorModelConfigurator
import kotlin.reflect.KClass

class ConfiguratorModelConfiguratorExtension(val delegate: ConfiguratorModelConfigurator) {
    inline fun <reified T> configuration() = delegate.configuration(T::class.java)!!.let { this }

    fun configuration(model: KClass<*>) = delegate.configuration(model.java)!!.let { this }

    fun configuration(section: String, model: KClass<*>) = delegate.configuration(section, model.java).let { this }
}
