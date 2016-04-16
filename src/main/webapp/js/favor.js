'use strict';

angular.module('payForward.favor', ['ngRoute', 'ngResource'])
    .controller('AddFavorCtrl', function (Category, Favor, UserFavor) {
        var vm = this;

        vm.category = {};
        vm.userFavor = {};

        vm.categories = Category.query();
        vm.favors = Favor.query();

        vm.save = function (valid, favorType) {
            if (valid) {
                vm.userFavor.type = favorType;
                UserFavor.save(vm.userFavor);
            }
        };
    })
    .factory('Category', function ($resource) {
        return $resource('/category', {});
    })
    .factory('Favor', function ($resource) {
        return $resource('/favor', {});
    })
    .factory('UserFavor', function ($resource) {
        return $resource('/user/favor', {});
    });
