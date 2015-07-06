'use strict';

 angular.module('niv', ['resource.invoice',
    'resource.magacinskaKartica',
 	'angular-md5'])

 .controller('nivAllCtrl', function (Artikal, MagacinskaKartica, $scope, $routeParams, $modal, $modalInstance, $log, $location, InvoiceItem) {
	
	$scope.close = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.nivelisi = function () {
		var invoiceId = $scope.modalID;
		if(invoiceId){
			Artikal.nivelisi({'invoiceId':invoiceId});
		}
		$modalInstance.dismiss('cancel');
        window.location.reload();
	};

});

