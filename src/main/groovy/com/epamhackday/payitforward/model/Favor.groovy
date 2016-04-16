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
@Document(collection = Favor.COLLECTION_NAME)
class Favor {
    public static final String COLLECTION_NAME = "favor";

    @Id
    Long id
    String name
    Category category

}
