package com.epamhackday.payitforward.model.request

import com.epamhackday.payitforward.model.Favor
import com.epamhackday.payitforward.model.FavorType
import groovy.transform.Canonical
import groovy.transform.ToString

import javax.validation.constraints.NotNull

@ToString(includeNames = true)
@Canonical
class AddUserFavor {
    @NotNull
    Favor favor
    @NotNull
    String description
    @NotNull
    FavorType type
}
