'use strict';

/* Controllers */

var warehouseControllers = angular.module('warehouseControllers', []);

warehouseControllers.controller('LagerCtrl', ['$scope', 'Article',
  function($scope, Article) {
    $scope.articles = Article.query();
    $scope.orderProp = 'age';
    $scope.reverseSort = false;
  }]);
