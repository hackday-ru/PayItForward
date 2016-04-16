var dealsServices = angular.module('dealsServices', ['ngResource']);

dealsServices.factory('IncomingDeals', ['$resource',
  function($resource){
    return $resource('/deal/incoming');
  }]);

dealsServices.factory('OutgoingDeals', ['$resource',
  function($resource){
    return $resource('/deal/outgoing');
  }]);

dealsServices.factory('AcceptDeal', ['$resource',
  function($resource){
    return $resource('/deal/accept/:dealId', {dealId:'@dealId'});
  }]);

dealsServices.factory('RejectDeal', ['$resource',
  function($resource){
    return $resource('/deal/reject/:dealId', {dealId:'@dealId'});
  }]);