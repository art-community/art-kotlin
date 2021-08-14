package io.art.meta.kotlin

import io.art.core.collection.ImmutableLazyArrayImplementation
import io.art.core.collection.ImmutableLazyMapImplementation
import io.art.meta.transformer.MetaTransformer
import java.util.function.Supplier

class KotlinLazyTransformer(private val parameterTransformer: MetaTransformer<Any>) : MetaTransformer<Lazy<*>> {
    override fun toArray(value: Lazy<*>): MutableList<*> = parameterTransformer.toArray(value.value)

    override fun toMap(value: Lazy<*>): MutableMap<*, *> = parameterTransformer.toMap(value.value)

    override fun toByteArray(value: Lazy<*>): ByteArray = parameterTransformer.toByteArray(value.value)

    override fun toString(value: Lazy<*>): String = parameterTransformer.toString(value.value)

    override fun toInteger(value: Lazy<*>): Int = parameterTransformer.toInteger(value.value)

    override fun toLong(value: Lazy<*>): Long = parameterTransformer.toLong(value.value)

    override fun toFloat(value: Lazy<*>): Float = parameterTransformer.toFloat(value.value)

    override fun toDouble(value: Lazy<*>): Double = parameterTransformer.toDouble(value.value)

    override fun toShort(value: Lazy<*>): Short = parameterTransformer.toShort(value.value)

    override fun toByte(value: Lazy<*>): Byte = parameterTransformer.toByte(value.value)

    override fun toCharacter(value: Lazy<*>): Char = parameterTransformer.toCharacter(value.value)

    override fun toBoolean(value: Lazy<*>): Boolean = parameterTransformer.toBoolean(value.value)

    override fun toLazyArray(value: Lazy<*>): ImmutableLazyArrayImplementation<*> = parameterTransformer.toLazyArray(value.value)

    override fun toLazyMap(value: Lazy<*>): ImmutableLazyMapImplementation<*, *> = parameterTransformer.toLazyMap(value.value)

    override fun toLazy(value: Lazy<*>): Supplier<*> = parameterTransformer.toLazy(value.value)

    override fun fromArray(value: MutableList<*>): Lazy<*> = lazy { parameterTransformer.fromArray(value) }

    override fun fromMap(value: MutableMap<*, *>): Lazy<*> = lazy { parameterTransformer.fromMap(value) }

    override fun fromByteArray(value: ByteArray): Lazy<*> = lazy { parameterTransformer.fromByteArray(value) }

    override fun fromString(value: String): Lazy<*> = lazy { parameterTransformer.fromString(value) }

    override fun fromInteger(value: Int): Lazy<*> = lazy { parameterTransformer.fromInteger(value) }

    override fun fromLong(value: Long): Lazy<*> = lazy { parameterTransformer.fromLong(value) }

    override fun fromFloat(value: Float): Lazy<*> = lazy { parameterTransformer.fromFloat(value) }

    override fun fromDouble(value: Double): Lazy<*> = lazy { parameterTransformer.fromDouble(value) }

    override fun fromShort(value: Short): Lazy<*> = lazy { parameterTransformer.fromShort(value) }

    override fun fromCharacter(value: Char): Lazy<*> = lazy { parameterTransformer.fromCharacter(value) }

    override fun fromBoolean(value: Boolean): Lazy<*> = lazy { parameterTransformer.fromBoolean(value) }

    override fun fromByte(value: Byte): Lazy<*> = lazy { parameterTransformer.fromByte(value) }

    override fun fromLazyArray(value: ImmutableLazyArrayImplementation<*>): Lazy<*> = lazy { parameterTransformer.fromLazyArray(value) }

    override fun fromLazyMap(value: ImmutableLazyMapImplementation<*, *>): Lazy<*> = lazy { parameterTransformer.fromLazyMap(value) }

    override fun fromLazy(value: Supplier<*>): Lazy<*> = lazy { parameterTransformer.fromLazy(value) }
}
