package com.epamhackday.payitforward.controller

import com.epamhackday.payitforward.model.Deal
import com.epamhackday.payitforward.model.Status
import com.epamhackday.payitforward.repository.DealRepository
import com.epamhackday.payitforward.repository.UserRepository
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

    @Value('${test.user.id}')
    String userId

    @Autowired
    UserRepository userRepository

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
    String get(@PathVariable String id) {
        def jsonBuilder = new JsonBuilder(dealRepository.findOne(id));
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
        def userId = getUserId()
        def jsonBuilder = new JsonBuilder(dealRepository.findByAcceptorUserIdOrderByDate(userId))
        //def  jsonBuilder = new JsonBuilder(dealRepository.findAll())
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/outgoing", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String getOutgoingDeals() {
        def userId = getUserId()
        def jsonBuilder = new JsonBuilder(dealRepository.findByInitiatorUserIdOrderByDate(userId))
        //def  jsonBuilder = new JsonBuilder(dealRepository.findAll())
        return jsonBuilder.toPrettyString()
    }

    @RequestMapping(value = "/reject/{dealId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String reject(@PathVariable String dealId) {
        return updateDealStatus(dealId, Status.REJECTED)
    }

    @RequestMapping(value = "/accept/{dealId}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String accept(@PathVariable String dealId) {
        return updateDealStatus(dealId, Status.ACCEPTED)
    }

    private String updateDealStatus(String dealId, Status status) {
        def userId = getUserId()
        def deal = dealRepository.findOne(dealId)
        if (userId.equals(deal?.acceptor?.user?.id)) {
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

    private String getUserId() {
        return userRepository.findUserByName(userName).id
    }
}
