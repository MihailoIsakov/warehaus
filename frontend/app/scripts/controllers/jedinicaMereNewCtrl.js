'use strict';

angular.module('jedinicaMere', [])

.controller('jedinicaMereNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedJedinicaMere':$scope.selectedJedinicaMere,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};	

});
