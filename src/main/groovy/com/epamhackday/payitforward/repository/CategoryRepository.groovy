package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Category
import org.springframework.data.mongodb.repository.MongoRepository


interface CategoryRepository extends MongoRepository<Category, Long> {

}