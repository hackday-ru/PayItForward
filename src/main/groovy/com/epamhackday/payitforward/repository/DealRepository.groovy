package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Deal
import org.springframework.data.mongodb.repository.MongoRepository

interface DealRepository extends MongoRepository<Deal, Long> {
    List<Deal> findByInitiatorUserEmail(String userEmail)

    List<Deal> findByInitiatorUserNameOrderByDate(String userName)

    List<Deal> findByAcceptorUserNameOrderByDate(String userName)
}