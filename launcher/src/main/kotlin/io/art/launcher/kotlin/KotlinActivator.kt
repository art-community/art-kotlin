package io.art.launcher.kotlin

import io.art.launcher.Activator
import io.art.launcher.Activator.activator
import io.art.launcher.ModulesInitializer.modules

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
