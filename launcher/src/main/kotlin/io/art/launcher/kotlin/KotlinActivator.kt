package io.art.launcher.kotlin

import io.art.configurator.module.ConfiguratorActivator
import io.art.configurator.module.ConfiguratorInitializer
import io.art.http.module.HttpActivator
import io.art.http.module.HttpInitializer
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
import io.art.transport.module.TransportActivator
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

