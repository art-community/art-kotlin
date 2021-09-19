package io.art.tests.kotlin

import io.art.launcher.Activator
import io.art.tests.module.TestsActivator
import io.art.tests.module.TestsInitializer


fun Activator.tests(configurator: TestsInitializer.() -> Any) {
    val activator = TestsActivator.tests { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}
