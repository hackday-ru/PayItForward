package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.User
import org.springframework.data.mongodb.repository.MongoRepository


interface UserRepository extends MongoRepository<User, String> {

}