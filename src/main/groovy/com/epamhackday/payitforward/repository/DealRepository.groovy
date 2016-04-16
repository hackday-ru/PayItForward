package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Deal
import org.springframework.data.mongodb.repository.MongoRepository


interface DealRepository extends MongoRepository<Deal, Long> {
    public List<Deal> findByUserId(Long userId)
}