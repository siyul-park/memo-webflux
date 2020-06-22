package com.ara.memo.dao

import com.ara.memo.entity.Entity
import org.reactivestreams.Publisher
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

class ReactiveDaoAdapter<R: ReactiveCrudRepository<T, ID>, T: Entity<ID>, ID>(
    private val repository: R
) : ReactiveDao<T, ID> {
    override fun <S : T> update(entity: S, updater: S.() -> Unit): Mono<S> = Mono.fromCallable { entity.apply(updater) }
        .flatMap { save(it) }

    override fun <S : T> save(entity: S): Mono<S> = repository.save(entity)

    override fun <S : T> saveAll(entities: Iterable<S>) = repository.saveAll(entities)

    override fun <S : T> saveAll(entityStream: Publisher<S>) = repository.saveAll(entityStream)

    override fun findById(id: ID) = repository.findById(id)

    override fun findById(id: Publisher<ID>) = repository.findById(id)

    override fun existsById(id: ID) = repository.existsById(id)

    override fun existsById(id: Publisher<ID>) = repository.existsById(id)

    override fun findAll() = repository.findAll()

    override fun findAllById(ids: Iterable<ID>) = repository.findAllById(ids)

    override fun findAllById(idStream: Publisher<ID>) = repository.findAllById(idStream)

    override fun count() = repository.count()

    override fun deleteById(id: ID) = repository.deleteById(id)

    override fun deleteById(id: Publisher<ID>) = repository.deleteById(id)

    override fun delete(entity: T) = repository.delete(entity)

    override fun deleteAll(entities: Iterable<T>) = repository.deleteAll(entities)

    override fun deleteAll(entityStream: Publisher<out T>) = repository.deleteAll(entityStream)

    override fun deleteAll() = repository.deleteAll()
}