'use strict';

angular.module('grupaArtikala', [])

.controller('grupaArtikalaNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedGrupaArtikala':$scope.selectedGrupaArtikala,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};	

	$scope.selektujPreduzece = function () {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/preduzece-modal.html',
			controller: 'preduzecaModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedPreduzece = data.selectedPreduzece;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedGrupaArtikala.preduzece = selectedPreduzece;
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
