package com.ara.memo.database.dao

import com.ara.memo.database.entity.Entity
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler

open class ScheduledDao<T: Entity<ID>, ID>(
    protected val dao: ReactiveDao<T, ID>,
    protected val scheduler: Scheduler
) : ReactiveDao<T, ID> {
    override fun <S : T> save(entity: S): Mono<S> = dao.save(entity).subscribeOn(scheduler)

    override fun <S : T> saveAll(entities: Iterable<S>) = dao.saveAll(entities).subscribeOn(scheduler)

    override fun <S : T> saveAll(entityStream: Publisher<S>) = dao.saveAll(entityStream).subscribeOn(scheduler)

    override fun findById(id: ID) = dao.findById(id).subscribeOn(scheduler)

    override fun findById(id: Publisher<ID>) = dao.findById(id).subscribeOn(scheduler)

    override fun existsById(id: ID) = dao.existsById(id).subscribeOn(scheduler)

    override fun existsById(id: Publisher<ID>) = dao.existsById(id).subscribeOn(scheduler)

    override fun findAll() = dao.findAll().subscribeOn(scheduler)

    override fun findAllById(ids: Iterable<ID>) = dao.findAllById(ids).subscribeOn(scheduler)

    override fun findAllById(idStream: Publisher<ID>) = dao.findAllById(idStream).subscribeOn(scheduler)

    override fun count() = dao.count().subscribeOn(scheduler)

    override fun deleteById(id: ID) = dao.deleteById(id).subscribeOn(scheduler)

    override fun deleteById(id: Publisher<ID>) = dao.deleteById(id).subscribeOn(scheduler)

    override fun delete(entity: T) = dao.delete(entity).subscribeOn(scheduler)

    override fun deleteAll(entities: Iterable<T>) = dao.deleteAll(entities).subscribeOn(scheduler)

    override fun deleteAll(entityStream: Publisher<out T>) = dao.deleteAll(entityStream).subscribeOn(scheduler)

    override fun deleteAll() = dao.deleteAll().subscribeOn(scheduler)
}