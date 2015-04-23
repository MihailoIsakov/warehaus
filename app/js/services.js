'use strict';

/* Services */

var warehouseServices = angular.module('warehouseServices', ['ngResource']);

warehouseServices.factory('Article', ['$resource',
  function($resource){
    return $resource('mocs/:articleId.json', {}, {
      query: {method:'GET', params:{articleId:'lagerList'}, isArray:true}
    });
  }]);
