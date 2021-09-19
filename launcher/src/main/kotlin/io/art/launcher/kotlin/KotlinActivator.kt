package io.art.launcher.kotlin

import io.art.launcher.Activator
import io.art.launcher.Activator.activator

fun activator(action: Activator.() -> Any) {
    action(activator())
}

fun activator(arguments: Array<String>, action: Activator.() -> Any) {
    action(activator(arguments))
}
