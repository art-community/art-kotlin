package io.art.tests.kotlin

import io.art.launcher.Activator
import io.art.tests.module.TestsActivator
import io.art.tests.module.TestsInitializer
import kotlin.reflect.KClass


fun Activator.tests(configurator: TestsInitializer.() -> Any) {
    val activator = TestsActivator.tests { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}

fun TestsInitializer.suit(suitClass: KClass<*>): TestsInitializer = suit(suitClass.java)

inline fun <reified T> TestsInitializer.suit(): TestsInitializer = suit(T::class)
