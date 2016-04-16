package com.epamhackday.payitforward.repository

import com.epamhackday.payitforward.model.Status
import com.epamhackday.payitforward.model.Type
import com.epamhackday.payitforward.model.UserFavor
import org.springframework.data.mongodb.repository.MongoRepository


interface UserFavorRepository extends MongoRepository<UserFavor, Long> {
    public List<UserFavor> findByUserEmailAndType(String userEmail, Type type)
    public List<UserFavor> findByFavorIdAndType(Long favorId, Type type)
}
