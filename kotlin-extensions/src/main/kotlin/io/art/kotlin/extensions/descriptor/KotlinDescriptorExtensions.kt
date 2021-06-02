package io.art.kotlin.extensions.descriptor

import io.art.json.module.JsonModule.jsonModule
import io.art.message.pack.module.MessagePackModule.messagePackModule
import io.art.protobuf.module.ProtobufModule.protobufModule
import io.art.value.module.ValueModule.model

inline fun <reified T> json(data: String): T = model(T::class.java, jsonModule().configuration().reader.read(data))

inline fun <reified T> protobuf(data: ByteArray): T = model(T::class.java, protobufModule().configuration().reader.read(data))

inline fun <reified T> messagePack(data: ByteArray): T = model(T::class.java, messagePackModule().configuration().reader.read(data))


inline fun <reified T> String.parseJson(): T = model(T::class.java, jsonModule().configuration().reader.read(this))

inline fun <reified T> ByteArray.parseProtobuf(): T = model(T::class.java, protobufModule().configuration().reader.read(this))

inline fun <reified T> ByteArray.parseMessagePack(): T = model(T::class.java, messagePackModule().configuration().reader.read(this))
