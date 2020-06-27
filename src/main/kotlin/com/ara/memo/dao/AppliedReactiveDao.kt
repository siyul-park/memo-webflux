package com.ara.memo.dao

import com.ara.memo.entity.Entity
import com.ara.memo.util.plugin.Plugin
import com.ara.memo.util.plugin.apply
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

class AppliedReactiveDao<R: ReactiveDao<T, ID>, T: Entity<ID>, ID>(
    private val dao: R,
    private val plugin: Plugin
) : ReactiveDao<T, ID> {
    override fun <S : T> save(entity: S): Mono<S> = dao.save(entity).apply(plugin)

    override fun <S : T> saveAll(entities: Iterable<S>) = dao.saveAll(entities).apply(plugin)

    override fun <S : T> saveAll(entityStream: Publisher<S>) = dao.saveAll(entityStream).apply(plugin)

    override fun <S : T> update(entity: S, updater: S.() -> Unit) = dao.update(entity, updater).apply(plugin)

    override fun findById(id: ID) = dao.findById(id).apply(plugin)

    override fun findById(id: Publisher<ID>) = dao.findById(id).apply(plugin)

    override fun existsById(id: ID) = dao.existsById(id).apply(plugin)

    override fun existsById(id: Publisher<ID>) = dao.existsById(id).apply(plugin)

    override fun findAll() = dao.findAll().apply(plugin)

    override fun findAllById(ids: Iterable<ID>) = dao.findAllById(ids).apply(plugin)

    override fun findAllById(idStream: Publisher<ID>) = dao.findAllById(idStream).apply(plugin)

    override fun count() = dao.count().apply(plugin)

    override fun deleteById(id: ID) = dao.deleteById(id).apply(plugin)

    override fun deleteById(id: Publisher<ID>) = dao.deleteById(id).apply(plugin)

    override fun delete(entity: T) = dao.delete(entity).apply(plugin)

    override fun deleteAll(entities: Iterable<T>) = dao.deleteAll(entities).apply(plugin)

    override fun deleteAll(entityStream: Publisher<out T>) = dao.deleteAll(entityStream).apply(plugin)

    override fun deleteAll() = dao.deleteAll().apply(plugin)
}