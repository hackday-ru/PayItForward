'use strict';

angular.module('payForwardApp', ['ngRoute', 'ngMaterial', 'payForward.user', 'payForward.deal'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/deals.html',
                controller: 'DealCtrl'
            })
            .otherwise({redirectTo: '/error'});
    }])
    .controller('MenuCtrl', MenuController);

function MenuController($scope, $location) {
    $scope.selectedMenu = 0;

    $scope.selectMenu = function (tabName) {
        $location.url('/' + tabName);
    }
}