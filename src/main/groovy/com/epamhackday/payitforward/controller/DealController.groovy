package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Deal
import com.epamhackday.payitforward.model.Status
import com.epamhackday.payitforward.repository.DealRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
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

    @Autowired
    ConfigurableApplicationContext applicationContext

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

    @RequestMapping(value = "/incoming", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String getIncomingDeals() {
        def userName = getUserName()
        def jsonBuilder = new JsonBuilder(dealRepository.findByAcceptorUserNameOrderByDate(userName))
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/outgoing", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String getOutgoingDeals() {
        def userName = getUserName()
        def jsonBuilder = new JsonBuilder(dealRepository.findByInitiatorUserNameOrderByDate(userName))
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/reject/{dealId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String reject(@PathVariable Long dealId) {
        return updateDealStatus(dealId, Status.REJECTED)
    }

    @RequestMapping(value = "/accept/{dealId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String accept(@PathVariable Long dealId) {
        return updateDealStatus(dealId, Status.ACCEPTED)
    }

    private String updateDealStatus(long dealId, Status status) {
        def userName = getUserName()
        def dealToReject = dealRepository.findOne(dealId)
        if (userName.equals(dealToReject?.acceptor?.user?.name)) {
            if (dealToReject.status == Status.PENDING) {
                dealToReject.status = status
                def jsonBuilder = new JsonBuilder(dealRepository.save(dealToReject))
                return jsonBuilder.toPrettyString()
            } else {
                throw new IllegalStateException("Deal is not in pending state")
            }
        } else {
            throw new SecurityException("Trying to reject other user's deal")
        }
    }

    private String getUserName() {
        return "test"
    }
}
