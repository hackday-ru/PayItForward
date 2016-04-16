'use strict';

angular.module('payForward.user', ['ngRoute', 'ngResource', 'ngResource'])
    .controller('UserCtrl', function ($scope, UserService) {


        $scope.getCurrentUser = function() {
            UserService.getCurrentUser(function(data) {
                $scope.currentUser = data;
            })
        }

    })
    .service('UserService', function ($resource) {
        return $resource('/user/current', {
            getCurrentUser: {
                method: 'GET',
                url: '/user/current'
            }
        })
    });
