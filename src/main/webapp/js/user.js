'use strict';

angular.module('payForward.user', ['ngRoute', 'ngResource'])
    .controller('UserCtrl', function (User) {
        var vm = this;
        vm.currentUser = {};

        vm.getCurrentUser = function() {
            vm.currentUser = User.get();
        }

        vm.getServiceInfo = function () {

        }


    })
    .factory('User', function ($resource) {
        return $resource('/user/current', {});
    })
    .factory('UserCan', function ($resource) {
        return $resource('/user/favor/:favorType');
    });

