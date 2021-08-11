package io.art.launcher.kotlin

import io.art.launcher.Activator
import io.art.launcher.Activator.activator
import io.art.launcher.ModulesInitializer
import io.art.launcher.ModulesInitializer.modules

fun Activator.activator(action: Activator.() -> Any) {
    action(activator())
}

fun Activator.activator(arguments: Array<String>, action: Activator.() -> Any) {
    action(activator(arguments))
}

fun Activator.kit(action: ModulesInitializer.() -> Any) {
    val modules = modules()
    action(modules)
    kit(modules)
}
