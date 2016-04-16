package com.epamhackday.payitforward.model

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.Canonical
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ToString(includeNames = true)
@Canonical
@Document
class User {
    @Id
    String id
    String name
    String email
    @JsonIgnore
    String password
}
