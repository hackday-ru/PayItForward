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

        vm.refreshFavors = function () {
            if (vm.category.id) {
                vm.favors = Favor.query({categoryId: vm.category.id})
            }
        };
    })
    .controller('FavorCtrl', function($scope, $location, UserFavorTypeService) {

         UserFavorTypeService.getFavorByType({favorType: 'CAN'}, {},
         function (data) {
                         $scope.userCanList = data;
                     });
          UserFavorTypeService.getFavorByType({favorType: 'WANT'}, {},
          function (data) {
                          $scope.userWantList = data;
                      });


        $scope.go = function(path) {
            $location.url(path);
        }
    })
    .factory('Category', function ($resource) {
        return $resource('/category', {});
    })
    .factory('Favor', function ($resource) {
        return $resource('/favor', {});
    })
    .factory('UserFavor', function ($resource) {
        return $resource('/user/favor', {});
    })
    .service('UserFavorTypeService', function ($resource) {
        return $resource('/user/favor/:favorType', {}, {
            getFavorByType: {
                method: 'GET',
                isArray: true,
                url: '/user/favor/:favorType',
                params: {
                    favorType: '@favorType'
                }
            }
        });
    });
//    .factory('FavorType', function ($resource) {
//        return $resource('/user/favor/:favorType', {});
//    });

