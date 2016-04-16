package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Deal
import com.epamhackday.payitforward.model.Status
import com.epamhackday.payitforward.repository.DealRepository
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/deal")
class DealController {

    @Autowired
    DealRepository dealRepository

    @Value('${test.user.name}')
    String userName

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
        def deal = dealRepository.findOne(dealId)
        if (userName.equals(deal?.acceptor?.user?.name)) {
            if (deal.status == Status.PENDING) {
                deal.status = status
                def jsonBuilder = new JsonBuilder(dealRepository.save(deal))
                return jsonBuilder.toPrettyString()
            } else {
                throw new IllegalStateException("Deal is not in pending state")
            }
        } else {
            throw new SecurityException("Trying to reject other user's deal")
        }
    }

    private String getUserName() {
        return userName
    }
}
