package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Deal
import com.epamhackday.payitforward.repository.DealRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Created by bu3apd on 4/16/2016.
 */
@Controller
@RequestMapping("/deal")
class DealController {

    @Autowired
    DealRepository dealRepository

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    String delete(@RequestBody Deal deal) {
        def jsonBuilder = new JsonBuilder(dealRepository.delete(deal.id));
        return "Deal with id " + deal.id + " successfully deleted";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String save(@RequestBody Deal deal) {
        def jsonBuilder = new JsonBuilder(dealRepository.save(deal));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String get(@PathVariable Long id) {
        def jsonBuilder = new JsonBuilder(dealRepository.findById(id));
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String list() {
        def jsonBuilder = new JsonBuilder(dealRepository.findAll())
        return jsonBuilder.toPrettyString()
    }
}
