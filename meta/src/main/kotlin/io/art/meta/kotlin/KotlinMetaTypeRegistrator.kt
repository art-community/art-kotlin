package io.art.meta.kotlin

import io.art.core.caster.Caster.cast
import io.art.meta.constants.MetaConstants.MetaTypeInternalKind.*
import io.art.meta.model.MetaType
import io.art.meta.model.TransformersComputer.computeInputTransformer
import io.art.meta.model.TransformersComputer.computeOutputTransformer
import io.art.meta.module.MetaInitializer
import io.art.meta.transformer.CustomTransformers


internal fun MetaInitializer.registerKotlinMetaTypes(): MetaInitializer {
    MetaType.builder<Unit>()
            .type(Unit::class.java)
            .internalKind(VOID)
            .build()
            .let(::registerCustomType)
    MetaType.builder<Nothing>()
            .type(Nothing::class.java)
            .internalKind(VOID)
            .build()
            .let(::registerCustomType)
    MetaType.builder<Function0<*>>()
            .type(Function0::class.java)
            .internalKind(SUPPLIER)
            .build()
            .let(::registerCustomType)
    MetaType.builder<Sequence<*>>()
            .type(Sequence::class.java)
            .internalKind(STREAM)
            .build()
            .let(::registerCustomType)
    MetaType.builder<Lazy<*>>()
            .type(Lazy::class.java)
            .internalKind(LAZY)
            .build()
            .let(::registerCustomType)
    MetaType.builder<Pair<*, *>>()
            .type(Pair::class.java)
            .internalKind(MAP)
            .build()
            .let(::registerCustomType)
    return this
}

internal fun MetaInitializer.registerKotlinMetaTransformers(): MetaInitializer {
    registerFunctionTransformer()
    registerLazyTransformer()
    registerPairTransformer()
    registerSequenceTransformer()
    return this
}

private fun MetaInitializer.registerFunctionTransformer() {
    val input = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinFunctionTransformer(cast(computeInputTransformer(parameter)))
    }
    val output = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinFunctionTransformer(cast(computeOutputTransformer(parameter)))
    }
    registerCustomTransformers(Function0::class.java, CustomTransformers(input, output))
}

private fun MetaInitializer.registerLazyTransformer() {
    val input = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinLazyTransformer(cast(computeInputTransformer(parameter)))
    }
    val output = { type: MetaType<*> ->
        val parameter = type.parameters().get(0)
        KotlinLazyTransformer(cast(computeOutputTransformer(parameter)))
    }
    registerCustomTransformers(Lazy::class.java, CustomTransformers(input, output))
}

private fun MetaInitializer.registerPairTransformer() {
    val input = { _: MetaType<*> -> KotlinPairTransformer }
    val output = { _: MetaType<*> -> KotlinPairTransformer }
    registerCustomTransformers(Pair::class.java, CustomTransformers(input, output))
}

private fun MetaInitializer.registerSequenceTransformer() {
    val input = { _: MetaType<*> -> KotlinSequenceTransformer }
    val output = { _: MetaType<*> -> KotlinSequenceTransformer }
    registerCustomTransformers(Sequence::class.java, CustomTransformers(input, output))
}
