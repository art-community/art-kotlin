package io.art.message.pack.kotlin

import io.art.message.pack.MessagePack.messagePack
import io.art.meta.kotlin.definition
import io.art.meta.model.TypedObject.typed


inline fun <reified T> ByteArray.fromMessagePack(): T = messagePack().reader().read(definition<T>(), this) as T
inline fun <reified T> T.toMessagePack(): ByteArray = messagePack().writer().writeToBytes(typed(definition<T>(), this))
