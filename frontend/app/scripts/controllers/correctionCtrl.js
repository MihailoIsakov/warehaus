'use strict';

angular.module('correction', ['resource.artikale', 'angular-md5'])

.controller('correctionCtrl', function (Artikale, $scope, $modalInstance) {
	
	Artikale.query({'id':1}).$promise.then(function (data) {
			$scope.articled = data;
		});
	$scope.hello = 'Hello world';
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = {};	
	}
	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.addCor = function (newValue) {
		
	};
});
