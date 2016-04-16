package com.epamhackday.payitforward.repository

import org.springframework.data.mongodb.repository.MongoRepository


interface CategoryRepository extends MongoRepository<Category, Long> {

}