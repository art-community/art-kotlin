package io.art.tests.kotlin

import io.art.launcher.Activator
import io.art.tests.TestSuit
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

fun TestsInitializer.suit(suitClass: KClass<out TestSuit>): TestsInitializer = suit(suitClass.java)

inline fun <reified T : TestSuit> TestsInitializer.suit(): TestsInitializer = suit(T::class)
