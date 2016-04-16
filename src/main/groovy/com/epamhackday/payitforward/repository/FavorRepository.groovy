package com.epamhackday.payitforward.repository

import org.springframework.stereotype.Repository

@Repository
interface FavorRepository extends MongoRepository<Favor, Long> {
}
