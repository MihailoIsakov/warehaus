'use strict';

/* App Module */

var warehouseApp = angular.module('warehouseApp', [
  'ngRoute',
  'warehouseControllers',
  'warehouseServices'
]);


warehouseApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/lagerList', {
        templateUrl: 'partials/lager-list.html',
        controller: 'LagerCtrl'
      }).
      when('/analyticsReport', {
        templateUrl: 'partials/analytics-report.html',
        controller: 'AnalyticsReportCtrl'
      }).
      otherwise({
        redirectTo: '/analyticsReport'
      });
  }]);