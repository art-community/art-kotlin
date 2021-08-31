package io.art.rsocket.kotlin

import io.art.communicator.Connector
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaMethod
import io.art.rsocket.Rsocket.rsocketConnector
import io.art.rsocket.Rsocket.rsocketState
import io.art.rsocket.state.RsocketModuleState.RsocketLocalState

inline fun <reified T : Connector> rsocketConnector(): T = rsocketConnector(T::class.java) as T

inline fun <reified T : Connector> rsocketConnector(action: T.() -> Any): Any = action(rsocketConnector(T::class.java) as T)


inline fun <reified C, reified M : MetaClass<C>> rsocketState(crossinline method: (owner: M) -> MetaMethod<*>): RsocketLocalState = rsocketState(C::class.java) { owner: M -> method(owner) }

inline fun <reified C, reified M : MetaClass<C>> rsocketState(crossinline method: (owner: M) -> MetaMethod<*>, action: RsocketLocalState.() -> Any) {
    action(rsocketState(C::class.java) { owner: M -> method(owner) })
}
