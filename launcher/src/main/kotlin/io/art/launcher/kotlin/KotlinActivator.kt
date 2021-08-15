package io.art.launcher.kotlin

import io.art.configurator.module.ConfiguratorActivator
import io.art.configurator.module.ConfiguratorInitializer
import io.art.json.module.JsonActivator
import io.art.launcher.Activator
import io.art.launcher.Activator.activator
import io.art.launcher.TestingLauncher
import io.art.logging.module.LoggingActivator
import io.art.logging.module.LoggingInitializer
import io.art.message.pack.module.MessagePackActivator
import io.art.meta.kotlin.KotlinMetaActivator
import io.art.meta.model.MetaLibrary
import io.art.meta.module.MetaInitializer
import io.art.rsocket.module.RsocketActivator
import io.art.rsocket.module.RsocketInitializer
import io.art.scheduler.module.SchedulerActivator
import io.art.yaml.module.YamlActivator

fun activator(action: Activator.() -> Any) {
    action(activator())
}

fun activator(arguments: Array<String>, action: Activator.() -> Any) {
    action(activator(arguments))
}

fun testing(action: Activator.() -> Any) {
    val activator = activator()
    action(activator)
    TestingLauncher.testing { activator }
}

fun testing(arguments: Array<String>, action: Activator.() -> Any) {
    val activator = activator()
    action(activator)
    TestingLauncher.testing(arguments) { activator }
}

fun <T : MetaLibrary> Activator.meta(factory: () -> T) {
    module(KotlinMetaActivator.meta(factory))
}

fun <T : MetaLibrary> Activator.meta(factory: () -> T, initializer: (MetaInitializer) -> MetaInitializer) {
    module(KotlinMetaActivator.meta(factory, initializer))
}

fun Activator.configurator(configurator: ConfiguratorInitializer.() -> Any = {}) {
    val activator = ConfiguratorActivator.configurator { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}

fun Activator.logging(configurator: LoggingInitializer.() -> Any = {}) {
    val activator = LoggingActivator.logging { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}

fun Activator.json() {
    module(JsonActivator.json())
}

fun Activator.messagePack() {
    module(MessagePackActivator.messagePack())
}

fun Activator.yaml() {
    module(YamlActivator.yaml())
}

fun Activator.rsocket(configurator: RsocketInitializer.() -> Any) {
    val activator = RsocketActivator.rsocket { initializer ->
        configurator(initializer)
        initializer
    }
    module(activator)
}

fun Activator.scheduler() {
    module(SchedulerActivator.scheduler())
}
