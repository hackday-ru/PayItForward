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
    .controller('FavorCtrl', function($scope, $location, UserFavorService) {

         UserFavorService.getFavorByType({favorType: 'CAN'}, {},
         function (data) {
                         $scope.userCanList = data;
                     });
          UserFavorService.getFavorByType({favorType: 'WANT'}, {},
          function (data) {
                          $scope.userWantList = data;
                      });

//        $scope.userCan = FavorType.query({favorType: 'CAN'});
//        $scope.userWant = FavorType.query({favorType: 'WANT'});

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
    .service('UserFavorService', function ($resource) {
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

