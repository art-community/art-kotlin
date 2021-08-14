package io.art.rsocket.kotlin

import io.art.core.communication.Connector
import io.art.rsocket.Rsocket.rsocketConnector

inline fun <reified T : Connector> rsocketConnector(): T = rsocketConnector(T::class.java) as T

inline fun <reified T : Connector> rsocketConnector(action: T.() -> Any): Any = action(rsocketConnector(T::class.java) as T)
