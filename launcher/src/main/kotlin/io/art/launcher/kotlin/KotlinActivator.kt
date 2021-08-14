package io.art.launcher.kotlin

import io.art.launcher.Activator
import io.art.launcher.Activator.activator
import io.art.launcher.ModulesInitializer.modules
import io.art.meta.kotlin.KotlinMetaActivator
import io.art.meta.model.MetaLibrary
import io.art.meta.module.MetaInitializer

fun activator(action: Activator.() -> Any) {
    action(activator())
}

fun activator(arguments: Array<String>, action: Activator.() -> Any) {
    action(activator(arguments))
}

fun Activator.kit(action: KotlinModulesInitializer.() -> Any) {
    val modules = modules()
    action(KotlinModulesInitializer(modules))
    kit(modules)
}


fun <T : MetaLibrary> Activator.meta(factory: () -> T) {
    module(KotlinMetaActivator.meta(factory))
}

fun <T : MetaLibrary> Activator.meta(factory: () -> T, initializer: (MetaInitializer) -> MetaInitializer) {
    module(KotlinMetaActivator.meta(factory, initializer))
}
