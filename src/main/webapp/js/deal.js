'use strict';

angular.module('payForward.deal', ['ngRoute', 'ngResource','dealsServices'])
    .controller('DealCtrl', ['$scope', 'IncomingDeals', 'OutgoingDeals', 'AcceptDeal', 'RejectDeal',
     function ($scope, IncomingDeals, OutgoingDeals, AcceptDeal, RejectDeal) {
        $scope.incomingDeals = IncomingDeals.query();
        $scope.outgoingDeals = OutgoingDeals.query();

        $scope.acceptDeal = function(acceptedDealId, index){
            AcceptDeal.save({dealId:acceptedDealId}, function(deal){
                console.log("Accepted successfully")
                console.log(deal)
                $scope.incomingDeals[index] = deal;
            });
        }

        $scope.rejectDeal = function(rejectedDealId, index){
            RejectDeal.save({dealId:rejectedDealId}, function(deal){
                console.log("Rejected successfully")
                console.log(deal)
                $scope.incomingDeals[index] = deal;
            });
        }

        console.log($scope)
    }]);
