package io.art.kotlin.extensions.model.value

import io.art.model.configurator.ValueModelConfigurator
import java.lang.reflect.Type
import kotlin.reflect.KClass

class ValueModelConfiguratorExtension(val delegate: ValueModelConfigurator) {
    fun model(`object`: Any) = model(`object`::class)

    fun model(type: KClass<*>) = model(type.java)

    fun model(type: Type) = delegate.mapping(type).let { this }
}
