'use strict';

angular.module('preduzeceNew', [])

.controller('preduzeceNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedPreduzece':$scope.selectedPreduzece,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.selektujMesto = function (invoiceItem, size) {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/mesto-modal.html',
			controller: 'mestaModalCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			
			var selectedMesto = data.selectedMesto;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedPreduzece.mesto = selectedMesto;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
		
});
