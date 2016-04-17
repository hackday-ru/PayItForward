'use strict';

angular.module('payForward.search', ['ngRoute', 'ngResource', 'ui.bootstrap'])
    .controller('SearchCtrl', function (SearchService) {
        var vm = this;

        vm.users = [];

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
            vm.showGetFavors = false;
            vm.showComparison = false;
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
                    if (data) {
                        vm.favorsGet = [];
                        data.forEach(function(elem) {
                            vm.favorsGet.push({isChecked: false, data: elem})
                        });
                    }
                }
            )
        };

        vm.compareFavors = function() {
            vm.showComparison = true;
            vm.wantFavorIds = vm.favorsGet.filter(function (e) {
                return e.isChecked;
            }).map(function (e) {
                return e.data.id;
            });
            if (vm.canFavorId && vm.wantFavorIds) {
                vm.users = SearchService.getUsersByCansAndWants({
                    canFavorId: vm.canFavorId,
                    'wantFavorIds': vm.wantFavorIds
                })
            }
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
                url: '/user/favor/category/:categoryId/:favorType',
                isArray: true,
                params: {
                    categoryId: '@categoryId',
                    favorType: '@favorType'
                }
            },
            getUsersByCansAndWants: {
                method: 'GET',
                url: '/user/favor',
                isArray: true
            }
        });
    });
