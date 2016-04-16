'use strict';

angular.module('payForwardApp', ['ngRoute', 'payForward.user',
        'payForward.deal', 'payForward.favor',  'payForward.search'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/deals.html',
                controller: 'DealCtrl'
            })
            .when('/search', {
                templateUrl: 'views/search.html',
                controller: 'SearchCtrl'
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