'use strict';

angular.module('payForwardApp', ['ngRoute', 'payForward.user', 'payForward.deal', 'payForward.favor'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/deals.html',
                controller: 'DealCtrl'
            })
            .when('/add-new-favor', {
                templateUrl: 'views/add-favor.html',
                controller: 'AddFavorCtrl',
                controllerAs: 'addFavorCtrl'
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