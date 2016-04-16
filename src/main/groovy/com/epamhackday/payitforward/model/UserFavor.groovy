package com.epamhackday.payitforward.model

import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@ToString(includeNames = true)
@Canonical
@Document
class UserFavor {
    @Id
    String id
    @DBRef
    User user
    @DBRef
    Favor favor
    String description
    FavorType type
    boolean deleted
}
