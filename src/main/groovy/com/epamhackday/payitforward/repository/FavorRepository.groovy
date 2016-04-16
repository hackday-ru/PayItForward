package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Favor
import org.springframework.data.mongodb.repository.MongoRepository

interface FavorRepository extends MongoRepository<Favor, Long> {
}
