package io.art.meta.kotlin

import io.art.core.collection.ImmutableLazyArrayImplementation
import io.art.core.collection.ImmutableLazyMapImplementation
import io.art.meta.transformer.MetaTransformer
import java.util.function.Supplier

class KotlinFunctionTransformer(private val parameterTransformer: MetaTransformer<Any>) : MetaTransformer<Function0<*>> {
    override fun toArray(value: Function0<*>): MutableList<*> = parameterTransformer.toArray(value())

    override fun toMap(value: Function0<*>): MutableMap<*, *> = parameterTransformer.toMap(value())

    override fun toByteArray(value: Function0<*>): ByteArray = parameterTransformer.toByteArray(value())

    override fun toString(value: Function0<*>): String = parameterTransformer.toString(value())

    override fun toInteger(value: Function0<*>): Int = parameterTransformer.toInteger(value())

    override fun toLong(value: Function0<*>): Long = parameterTransformer.toLong(value())

    override fun toFloat(value: Function0<*>): Float = parameterTransformer.toFloat(value())

    override fun toDouble(value: Function0<*>): Double = parameterTransformer.toDouble(value())

    override fun toShort(value: Function0<*>): Short = parameterTransformer.toShort(value())

    override fun toByte(value: Function0<*>): Byte = parameterTransformer.toByte(value())

    override fun toCharacter(value: Function0<*>): Char = parameterTransformer.toCharacter(value())

    override fun toBoolean(value: Function0<*>): Boolean = parameterTransformer.toBoolean(value())

    override fun toLazyArray(value: Function0<*>): ImmutableLazyArrayImplementation<*> = parameterTransformer.toLazyArray(value())

    override fun toLazyMap(value: Function0<*>): ImmutableLazyMapImplementation<*, *> = parameterTransformer.toLazyMap(value())

    override fun toLazy(value: Function0<*>): Supplier<*> = Supplier { value() }

    override fun fromArray(value: MutableList<*>): Function0<*> = { parameterTransformer.fromArray(value) }

    override fun fromMap(value: MutableMap<*, *>): Function0<*> = { parameterTransformer.fromMap(value) }

    override fun fromByteArray(value: ByteArray): Function0<*> = { parameterTransformer.fromByteArray(value) }

    override fun fromString(value: String): Function0<*> = { parameterTransformer.fromString(value) }

    override fun fromInteger(value: Int): Function0<*> = { parameterTransformer.fromInteger(value) }

    override fun fromLong(value: Long): Function0<*> = { parameterTransformer.fromLong(value) }

    override fun fromFloat(value: Float): Function0<*> = { parameterTransformer.fromFloat(value) }

    override fun fromDouble(value: Double): Function0<*> = { parameterTransformer.fromDouble(value) }

    override fun fromShort(value: Short): Function0<*> = { parameterTransformer.fromShort(value) }

    override fun fromCharacter(value: Char): Function0<*> = { parameterTransformer.fromCharacter(value) }

    override fun fromBoolean(value: Boolean): Function0<*> = { parameterTransformer.fromBoolean(value) }

    override fun fromByte(value: Byte): Function0<*> = { parameterTransformer.fromByte(value) }

    override fun fromLazyArray(value: ImmutableLazyArrayImplementation<*>): Function0<*> = { parameterTransformer.fromLazyArray(value) }

    override fun fromLazyMap(value: ImmutableLazyMapImplementation<*, *>): Function0<*> = { parameterTransformer.fromLazyMap(value) }

    override fun fromLazy(value: Supplier<*>): Function0<*> = { value }
}
