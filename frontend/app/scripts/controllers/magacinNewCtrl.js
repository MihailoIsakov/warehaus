'use strict';

angular.module('magacin', [])

.controller('magacinNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedMagacin':$scope.selectedMagacin,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.selektujMesto = function () {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/mesto-modal.html',
			controller: 'mestaModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedMesto = data.selectedMesto;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedMagacin.mesto = selectedMesto;
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
