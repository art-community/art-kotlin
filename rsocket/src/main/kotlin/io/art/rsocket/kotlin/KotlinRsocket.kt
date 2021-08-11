package io.art.rsocket.kotlin

import io.art.communicator.model.Connector
import io.art.rsocket.Rsocket.rsocketConnector

inline fun <reified T : Connector> rsocketConnector(): T = rsocketConnector(T::class.java) as T

inline fun <reified T : Connector, R> rsocketConnector(action: T.() -> R): R = action(rsocketConnector(T::class.java) as T)
