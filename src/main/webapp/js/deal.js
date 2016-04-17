'use strict';

angular.module('payForward.deal', ['ngRoute', 'ngResource','dealsServices'])
    .controller('DealCtrl', ['$scope', 'IncomingDeals', 'OutgoingDeals', 'AcceptDeal', 'RejectDeal',
     function ($scope, IncomingDeals, OutgoingDeals, AcceptDeal, RejectDeal) {
        $scope.incomingDeals = IncomingDeals.query();
        $scope.outgoingDeals = OutgoingDeals.query();

        $scope.acceptDeal = function(acceptedDealId){
            AcceptDeal.save({dealId:acceptedDealId});
            $scope.incomingDeals = IncomingDeals.query();
        }

        $scope.rejectDeal = function(rejectedDealId){
            RejectDeal.save({dealId:rejectedDealId});
            $scope.incomingDeals = IncomingDeals.query();
        }

        console.log($scope)
    }]);
