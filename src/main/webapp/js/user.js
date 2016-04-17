'use strict';

angular.module('payForward.user', ['ngRoute', 'ngResource'])
    .controller('UserCtrl', function (User, UserFavor) {
        var vm = this;
        vm.currentUser = {};
        vm.userCan = {};
        vm.userWant = {};

        vm.getCurrentUser = function() {
            vm.currentUser = User.get();
        }

        vm.getUserFavor = function () {
            UserFavor.get({favorType: 'CAN'}, {}, function(data) {
                vm.userCan = data;
            });
            UserFavor.get({favorType: 'WANT'}, {}, function(data) {
                vm.userWant = data;
            });
        }


    })
    .factory('User', function ($resource) {
        return $resource('/user/current', {});
    })
    .factory('UserFavor', function ($resource) {
        return $resource('/user/favor/:favorType', {}, {
            method: 'GET',
            isArray: true,
            url: '/user/favor/:favorType',
            params: {
                favorType: '@favorType'
            }
        });
    });

