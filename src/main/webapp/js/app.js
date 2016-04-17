'use strict';

angular.module('payForwardApp', ['ngRoute', 'payForward.user',
        'payForward.deal', 'payForward.favor', 'payForward.search'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/profile.html',
                controller: 'UserCtrl',
                controllerAs: 'userCtrl'
            })
            .when('/profile', {
                templateUrl: 'views/profile.html',
                controller: 'UserCtrl',
                controllerAs: 'userCtrl'
            })
            .when('/deals', {
                templateUrl: 'views/deals.html',
                controller: 'DealCtrl',
                controllerAs: 'dealCtrl'
            })
            .when('/search', {
                templateUrl: 'views/search.html',
                controller: 'SearchCtrl',
                controllerAs: 'searchCtrl'
            })
            .when('/favor', {
                templateUrl: 'views/favor.html',
                controller: 'FavorCtrl'
            })
            .when('/add-my-favor', {
                templateUrl: 'views/add-favor.html',
                controller: 'AddFavorCtrl',
                controllerAs: 'addFavorCtrl'
            })
            .when('/giveFavors', {
                templateUrl: 'views/search.html',
                controller: 'SearchCtrl',
                controllerAs: 'searchCtrl'
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