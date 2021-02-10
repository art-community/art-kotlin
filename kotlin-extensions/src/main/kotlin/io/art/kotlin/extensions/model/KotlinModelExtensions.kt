package io.art.kotlin.extensions.model

import io.art.kotlin.extensions.model.module.ModuleModelConfiguratorExtension
import kotlin.reflect.KClass

inline fun <reified T> module(configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(T::class.java.simpleName)).delegate

inline fun Any.module(configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(this::class.java.simpleName)).delegate

fun module(mainClass: KClass<*>, configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(mainClass.simpleName!!)).delegate

fun module(mainClass: String, configurator: ModuleModelConfiguratorExtension.() -> ModuleModelConfiguratorExtension) =
        configurator(ModuleModelConfiguratorExtension(mainClass)).delegate
