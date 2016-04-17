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
            vm.userCan = UserFavor.query({favorType: 'CAN'});
            vm.userWant = UserFavor.query({favorType: 'WANT'});
        }

    })
    .factory('User', function ($resource) {
        return $resource('/user/current', {});
    })
    .factory('UserFavor', function ($resource) {
        return $resource('/user/favor/:favorType', {});
    });

