'use strict';

angular.module('payForward.search', ['ngRoute', 'ngResource', 'ui.bootstrap'])
    .controller('SearchCtrl', function (SearchService) {
        var vm = this;

        SearchService.getCategoriesByType({favorType: 'CAN'}, {},
            function(data) {
                console.log(data);
                vm.categoriesCan = data;
                if (vm.categoriesCan && vm.categoriesCan.length > 0) {
                    vm.selectCan(vm.categoriesCan[0].name);
                }
            });
        SearchService.getCategoriesByType({favorType: 'WANT'}, {},
            function(data) {
                console.log(data);
                vm.categoriesGet = data;
            });

        vm.selectCan = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'CAN', category: category}, {}, function(data) {
                    vm.favorsCan = data;
                }
            )
        };

        vm.selectGet = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'WANT', category: category}, {}, function(data) {
                    vm.favorsGet = data;
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
