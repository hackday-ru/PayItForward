'use strict';

angular.module('payForward.deal', ['ngRoute', 'ngResource','dealsServices'])
    .controller('DealCtrl', ['$scope', 'IncomingDeals', 'OutgoingDeals', 'AcceptDeal', 'RejectDeal',
     function ($scope, IncomingDeals, OutgoingDeals, AcceptDeal, RejectDeal) {
        $scope.incomingDeals = IncomingDeals.query();
        $scope.outgoingDeals = OutgoingDeals.query();

        $scope.acceptDeal = function(acceptedDealId){
            AcceptDeal.save({dealId:acceptedDealId});
        }

        $scope.rejectDeal = function(rejectedDealId){
            RejectDeal.save({dealId:rejectedDealId});
        }

        console.log($scope)
    }]);
