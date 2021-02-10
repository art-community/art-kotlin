package io.art.kotlin.extensions.model.value

import io.art.model.configurator.ValueModelConfigurator
import kotlin.reflect.KClass

class ValueModelConfiguratorExtension(val delegate: ValueModelConfigurator) : ValueModelConfigurator() {
    inline fun <reified T> mapping() = mapping(T::class)

    fun mapping(vararg types: Any) = types.forEach { type -> mapping(type::class) }

    fun mapping(vararg types: KClass<*>) = types.forEach { type -> delegate.mapping(type.java) }
}
