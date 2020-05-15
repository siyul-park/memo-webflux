package com.ara.memo.dao

import com.ara.memo.dao.plugin.Plugin
import com.ara.memo.entity.Entity
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

class AppliedReactiveDao<R: ReactiveDao<T, ID>, T: Entity<ID>, ID>(
    private val dao: R,
    private val plugin: Plugin
) : ReactiveDao<T, ID> {
    override fun <S : T> save(entity: S): Mono<S> = plugin.apply(dao.save(entity))

    override fun <S : T> saveAll(entities: Iterable<S>) = plugin.apply(dao.saveAll(entities))

    override fun <S : T> saveAll(entityStream: Publisher<S>) = plugin.apply(dao.saveAll(entityStream))

    override fun findById(id: ID) = plugin.apply(dao.findById(id))

    override fun findById(id: Publisher<ID>) = plugin.apply(dao.findById(id))

    override fun existsById(id: ID) = plugin.apply(dao.existsById(id))

    override fun existsById(id: Publisher<ID>) = plugin.apply(dao.existsById(id))

    override fun findAll() = plugin.apply(dao.findAll())

    override fun findAllById(ids: Iterable<ID>) = plugin.apply(dao.findAllById(ids))

    override fun findAllById(idStream: Publisher<ID>) = plugin.apply(dao.findAllById(idStream))

    override fun count() = plugin.apply(dao.count())

    override fun deleteById(id: ID) = plugin.apply(dao.deleteById(id))

    override fun deleteById(id: Publisher<ID>) = plugin.apply(dao.deleteById(id))

    override fun delete(entity: T) = plugin.apply(dao.delete(entity))

    override fun deleteAll(entities: Iterable<T>) = plugin.apply(dao.deleteAll(entities))

    override fun deleteAll(entityStream: Publisher<out T>) = plugin.apply(dao.deleteAll(entityStream))

    override fun deleteAll() = plugin.apply(dao.deleteAll())
}