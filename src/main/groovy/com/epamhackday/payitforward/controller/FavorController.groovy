package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Favor
import com.epamhackday.payitforward.repository.FavorRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Created by bu3apd on 4/16/2016.
 */
@Controller
@RequestMapping("/favor")
class FavorController {

    @Autowired
    FavorRepository favorRepository

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody Favor favor) {
        def jsonBuilder = new JsonBuilder(favorRepository.delete(favor.id));
        return "Favor with id " + favor.id + " successfully deleted";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String save(@RequestBody Favor favor) {
        def jsonBuilder = new JsonBuilder(favorRepository.save(favor));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String get(@PathVariable Long id) {
        def jsonBuilder = new JsonBuilder(favorRepository.findById(id));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String list() {
        def jsonBuilder = new JsonBuilder(favorRepository.findAll())
        return jsonBuilder.toPrettyString()
    }

}
