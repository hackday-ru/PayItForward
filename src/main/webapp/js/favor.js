'use strict';

angular.module('payForward.favor', ['ngRoute', 'ngResource'])
    .controller('AddFavorCtrl', function (Category, Favor) {
        var vm = this;

        vm.userFavor = {};

        vm.categories = Category.query();
        // $scope.favors = Favor.query();
    })
    .factory('Category', function ($resource) {
        return $resource('/category', {});
    })
    .factory('Favor', function ($resource) {
        return $resource('/favor', {});
    });
