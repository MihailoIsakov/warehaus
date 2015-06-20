'use strict';

angular.module('stonPrim', [])

.controller('stonPrimCtrl', function ($scope, $modalInstance, invoiceItem) {
	if($scope.selectedDoc){
		$scope.invoiceItem = "da";

	}
	else{
		$scope.invoiceItem = "ne";	
	}
	$scope.ok = function () {
		$modalInstance.close({'selectedDoc':$scope.selectedDoc,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	
});
