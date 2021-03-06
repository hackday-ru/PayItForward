package com.epamhackday.payitforward.model

import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ToString(includeNames = true)
@Canonical
@Document
class Category {
    @Id
    String id
    String name
    String parent
}
