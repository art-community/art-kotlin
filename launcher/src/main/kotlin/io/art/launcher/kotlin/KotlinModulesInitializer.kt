package io.art.launcher.kotlin

import io.art.configurator.module.ConfiguratorInitializer
import io.art.launcher.ModulesInitializer
import io.art.logging.module.LoggingInitializer
import io.art.rsocket.module.RsocketInitializer

class KotlinModulesInitializer(val delegate: ModulesInitializer) {
    fun rsocket(configurator: RsocketInitializer.() -> Any = { }) {
        delegate.rsocket { initializer ->
            configurator(initializer)
            initializer
        }
    }

    fun configurator(configurator: ConfiguratorInitializer.() -> Any = { }) {
        delegate.configurator { initializer ->
            configurator(initializer)
            initializer
        }
    }

    fun logging(configurator: LoggingInitializer.() -> Any = { }) {
        delegate.logging { initializer ->
            configurator(initializer)
            initializer
        }
    }

}
