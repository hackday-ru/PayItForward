package com.epamhackday.payitforward.model

import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by bu3apd on 4/16/2016.
 */
@ToString(includeNames = true)
@Canonical
@Document(collection = User.COLLECTION_NAME)
class User {

    public static final String COLLECTION_NAME = "user";

    @Id
    long id
    String name
    String email
    String password

}
