'use strict';

angular.module('artikal', [])

.controller('artikalNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedArtikal':$scope.selectedArtikal,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};	

	$scope.selektujGrupuArtikala = function () {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/grupe-artikala-modal.html',
			controller: 'grupeArtikalaModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedGrupaArtikala = data.selectedGrupaArtikala;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedArtikal.grupaArtikala = selectedGrupaArtikala;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}	
		
		$scope.selektujJedinicuMere = function () {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/jedinice-mere-modal.html',
			controller: 'jediniceMereModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedJedinicaMere = data.selectedJedinicaMere;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedArtikal.jedinicaMere = selectedJedinicaMere;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
});
