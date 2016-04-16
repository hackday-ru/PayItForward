'use strict';

angular.module('payForward.deal', ['ngRoute', 'ngResource','dealsServices'])
    .controller('DealCtrl', ['$scope', 'IncomingDeals', 'OutgoingDeals', function ($scope, IncomingDeals, OutgoingDeals) {
        $scope.incomingDeals = IncomingDeals.query();
        $scope.outgoingDeals = OutgoingDeals.query();
        console.log($scope)
    }]);
