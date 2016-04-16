package com.epamhackday.payitforward.repository

import org.springframework.data.mongodb.repository.MongoRepository


interface UserRepository extends MongoRepository<User, Long> {

}