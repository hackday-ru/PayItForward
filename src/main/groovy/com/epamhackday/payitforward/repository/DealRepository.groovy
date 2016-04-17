package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Deal
import org.springframework.data.mongodb.repository.MongoRepository

interface DealRepository extends MongoRepository<Deal, String> {
    List<Deal> findByInitiatorUserEmail(String userEmail)

    List<Deal> findByInitiatorUserIdOrderByDate(String userId)

    List<Deal> findByAcceptorUserIdOrderByDate(String userId)
}