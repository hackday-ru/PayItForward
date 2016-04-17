'use strict';

angular.module('payForward.user', ['ngRoute', 'ngResource'])
    .controller('UserCtrl', function (User, UserFavorService) {
        var vm = this;
        vm.currentUser = {};
        vm.userCan = {};
        vm.userWant = {};

        vm.getCurrentUser = function() {
            vm.currentUser = User.get();
        }

        vm.getUserFavor = function () {
            UserFavorService.getFavorUserByType({favorType: 'CAN'}, {}, function (data) {
                vm.userCan = data;
            });
            UserFavorService.getFavorUserByType({favorType: 'WANT'}, {}, function (data) {
                vm.userWant = data;
            });
        }

    })
    .factory('User', function ($resource) {
        return $resource('/user/current', {});
    })
    .service('UserFavorService', function ($resource) {
        return $resource('/user/favor/:favorType', {}, {
            getFavorUserByType: {
                method: 'GET',
                isArray: true,
                url: '/user/favor/:favorType',
                params: {
                    favorType: '@favorType'
                }
            }
        });
    });



