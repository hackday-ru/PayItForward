package com.epamhackday.payitforward.model

import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.ZonedDateTime

/**
 * Created by bu3apd on 4/16/2016.
 */
@ToString(includeNames = true)
@Canonical
@Document(collection = Deal.COLLECTION_NAME)
class Deal {

    public static final String COLLECTION_NAME = "deal";

    @Id
    Long id
    UserFavor initiator
    UserFavor acceptor
    Status status
    ZonedDateTime date
}
