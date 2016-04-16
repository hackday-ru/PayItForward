package com.epamhackday.payitforward.repository

import org.springframework.data.mongodb.repository.MongoRepository


interface UserFavorRepository extends MongoRepository<UserFavor, Long> {
}
