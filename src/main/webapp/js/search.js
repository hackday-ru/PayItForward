'use strict';

angular.module('payForward.search', ['ngRoute', 'ngResource', 'ui.bootstrap'])
    .controller('SearchCtrl', function ($scope, SearchService) {
        SearchService.getCategoriesByType({favorType: 'CAN'}, {},
            function(data) {
                console.log(data);
                $scope.categoriesCan = data;
                if ($scope.categoriesCan && $scope.categoriesCan.length > 0) {
                    $scope.selectCan($scope.categoriesCan[0].name);
                }
            });
        SearchService.getCategoriesByType({favorType: 'WANT'}, {},
            function(data) {
                console.log(data);
                $scope.categoriesGet = data;
            });

        $scope.selectCan = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'CAN', category: category}, {}, function(data) {
                    $scope.favorsCan = data;
                }
            )
        };

        $scope.selectGet = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'WANT', category: category}, {}, function(data) {
                    $scope.favorsGet = data;
                }
            )
        };
    })
    .service('SearchService', function ($resource) {
        return $resource('/user/favor/:id', {id: '@id'}, {
            getCategoriesByType: {
                method: 'GET',
                isArray: true,
                url: '/user/favor/type/:favorType',
                params: {
                    favorType: '@favorType'
                }
            },
            getFavorsByCategory: {
                method: 'GET',
                url: '/user/favor/category/:category/:favorType',
                isArray: true,
                params: {
                    category: '@category',
                    favorType: '@favorType'
                }
            }
        });
    });
