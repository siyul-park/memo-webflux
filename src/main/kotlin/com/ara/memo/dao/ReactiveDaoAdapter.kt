package com.ara.memo.dao

import com.ara.memo.dao.mapper.ExceptionMapper
import com.ara.memo.entity.Entity
import org.reactivestreams.Publisher
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

class ReactiveDaoAdapter<R: ReactiveCrudRepository<T, ID>, T: Entity<ID>, ID>(
    private val repository: R,
    private val exceptionMapper: ExceptionMapper
) : ReactiveDao<T, ID> {
    override fun <S : T> save(entity: S): Mono<S>
        = repository.save(entity).onErrorMap(exceptionMapper::map)

    override fun <S : T> saveAll(entities: Iterable<S>)
        = repository.saveAll(entities).onErrorMap(exceptionMapper::map)

    override fun <S : T> saveAll(entityStream: Publisher<S>)
        = repository.saveAll(entityStream).onErrorMap(exceptionMapper::map)

    override fun findById(id: ID)
        = repository.findById(id).onErrorMap(exceptionMapper::map)

    override fun findById(id: Publisher<ID>)
        = repository.findById(id).onErrorMap(exceptionMapper::map)

    override fun existsById(id: ID)
        = repository.existsById(id).onErrorMap(exceptionMapper::map)

    override fun existsById(id: Publisher<ID>)
        = repository.existsById(id).onErrorMap(exceptionMapper::map)

    override fun findAll()
        = repository.findAll().onErrorMap(exceptionMapper::map)

    override fun findAllById(ids: Iterable<ID>)
        = repository.findAllById(ids).onErrorMap(exceptionMapper::map)

    override fun findAllById(idStream: Publisher<ID>)
        = repository.findAllById(idStream).onErrorMap(exceptionMapper::map)

    override fun count()
        = repository.count().onErrorMap(exceptionMapper::map)

    override fun deleteById(id: ID)
        = repository.deleteById(id).onErrorMap(exceptionMapper::map)

    override fun deleteById(id: Publisher<ID>)
        = repository.deleteById(id).onErrorMap(exceptionMapper::map)

    override fun delete(entity: T)
        = repository.delete(entity).onErrorMap(exceptionMapper::map)

    override fun deleteAll(entities: Iterable<T>)
        = repository.deleteAll(entities).onErrorMap(exceptionMapper::map)

    override fun deleteAll(entityStream: Publisher<out T>)
        = repository.deleteAll(entityStream).onErrorMap(exceptionMapper::map)

    override fun deleteAll()
        = repository.deleteAll().onErrorMap(exceptionMapper::map)
}