var dealsServices = angular.module('dealsServices', ['ngResource']);

dealsServices.factory('IncomingDeals', ['$resource',
  function($resource){
    return $resource('/deal/incoming');
  }]);

dealsServices.factory('OutgoingDeals', ['$resource',
  function($resource){
    return $resource('/deal/outgoing');
  }]);