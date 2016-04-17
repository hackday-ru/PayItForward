'use strict';

angular.module('payForward.search', ['ngRoute', 'ngResource', 'ui.bootstrap'])
    .controller('SearchCtrl', function (SearchService) {
        var vm = this;

        vm.showGetFavors = false;
        vm.showComparison = false;

        SearchService.getCategoriesByType({favorType: 'CAN'}, {},
            function(data) {
                console.log(data);
                vm.categoriesCan = data;
                if (vm.categoriesCan && vm.categoriesCan.length > 0) {
                    vm.selectCanCategory(vm.categoriesCan[0]);
                }
            });

        SearchService.getCategoriesByType({favorType: 'WANT'}, {},
            function(data) {
                console.log(data);
                vm.categoriesGet = data;
            });

        vm.selectCanCategory = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'CAN', categoryId: category.id}, {}, function(data) {
                    vm.favorsCan = data;
                }
            )
        };

        vm.selectCanFavor = function (favor) {
            if (vm.categoriesGet && vm.categoriesGet.length > 0) {
                vm.selectGetCategory(vm.categoriesGet[0]);
            }
            vm.canFavorId = favor.id;
            vm.showGetFavors = true;
        };

        vm.selectGetCategory = function (category) {
            SearchService.getFavorsByCategory(
                {favorType: 'WANT', categoryId: category.id}, {}, function(data) {
                    vm.favorsGet = data;
                }
            )
        };

        vm.compareFavors = function() {
            vm.showComparison = true;
        }

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
                url: '/user/favor/category/:categoryId/:favorType',
                isArray: true,
                params: {
                    categoryId: '@categoryId',
                    favorType: '@favorType'
                }
            }
        });
    });
