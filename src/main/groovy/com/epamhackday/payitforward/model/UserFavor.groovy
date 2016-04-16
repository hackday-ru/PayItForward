package com.epamhackday.payitforward.model

import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by bu3apd on 4/16/2016.
 */

@ToString(includeNames = true)
@Canonical
@Document(collection = UserFavor.COLLECTION_NAME)
class UserFavor {

    public static final String COLLECTION_NAME = "userfavor";

    long id
    User user
    Favor favor
    String description
    boolean deleted

}
