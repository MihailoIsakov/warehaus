'use strict';

/* Controllers */

var warehouseControllers = angular.module('warehouseControllers', []);

warehouseControllers.controller('LagerCtrl', ['$scope', 'Article',
  function($scope, Article) {
    $scope.articles = Article.query();
    $scope.reverseSort = false;
  }]);

warehouseControllers.controller('AnalyticsReportCtrl', ['$scope', 'Analytics',
  function($scope, Analytics) {
    $scope.analytics = Analytics.query();
    $scope.reverseSort = false;
  }]);
