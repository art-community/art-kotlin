package io.art.http.kotlin

import io.art.communicator.Connector
import io.art.http.Http.httpConnector
import io.art.http.Http.httpState
import io.art.http.state.HttpLocalState
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaMethod
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : Connector> httpConnector(): T = httpConnector(T::class.java) as T

inline fun <reified T : Connector> httpConnector(action: T.() -> Any): Any = action(httpConnector(T::class.java) as T)


inline fun <reified C, reified M : MetaClass<C>> httpState(crossinline method: (owner: M) -> MetaMethod<*>): HttpLocalState = httpState(C::class.java) { owner: M -> method(owner) }

inline fun <reified C, reified M : MetaClass<C>> httpState(crossinline method: (owner: M) -> MetaMethod<*>, action: HttpLocalState.() -> Any) {
    action(httpState(C::class.java) { owner: M -> method(owner) })
}

val HttpLocalState.path
    get() = PropertyDelegateProvider { _: Any?, _ ->
        ReadOnlyProperty<Any?, String?> { _, property -> pathParameters()[property.name] }
    }

val HttpLocalState.query
    get() = PropertyDelegateProvider { _: Any?, _ ->
        ReadOnlyProperty<Any?, String?> { _, property -> queryParameters()[property.name] }
    }
