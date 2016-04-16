'use strict';

angular.module('payForward.deal', ['ngRoute', 'ngResource','dealsServices'])
    .controller('DealCtrl', ['$scope', 'IncomingDeals', 'OutgoingDeals', 'AcceptDeal', 'RejectDeal',
     function ($scope, IncomingDeals, OutgoingDeals, AcceptDeal, RejectDeal) {
        $scope.incomingDeals = IncomingDeals.query();
        $scope.outgoingDeals = OutgoingDeals.query();

        AcceptDeal.save({dealId:123});
        RejectDeal.save({dealId:321});
        console.log($scope)
    }]);
