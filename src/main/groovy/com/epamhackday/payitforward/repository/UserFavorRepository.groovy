package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.UserFavor
import org.springframework.data.mongodb.repository.MongoRepository


interface UserFavorRepository extends MongoRepository<UserFavor, Long> {
}
