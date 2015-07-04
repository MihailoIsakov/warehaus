'use strict';

angular.module('zaposleniNew', [])

.controller('zaposleniNewCtrl', function ($scope, $routeParams, $modal, $log, $location, $route, $modalInstance ) {

	$scope.ok = function () {
		
		$modalInstance.close({'selectedZaposleni':$scope.selectedZaposleni,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.selektujPreduzece = function (invoiceItem, size) {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/preduzece-modal.html',
			controller: 'preduzecaModalCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			
			var selectedPreduzece = data.selectedPreduzece;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedZaposleni.preduzece = selectedPreduzece;
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
		
	$scope.statusi = ["aktivan","penzionisan","neaktivan","probni","odsutan"];

	$scope.openDatumNastankaDatepicker = function($event, datumNastankaOpened) {
		$scope['datumKnjizenjaOpened'] = false;
		$event.preventDefault();
		$event.stopPropagation();
		$scope[datumNastankaOpened] = true;
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
				$scope.selectedZaposleni.mesto = selectedMesto;
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
		
		$scope.selektujRadnoMesto = function (invoiceItem, size) {

		
		var modalInstance = $modal.open({
			templateUrl: 'views/radnomesto-modal.html',
			controller: 'radnoMestoModalCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			
			var selectedMesto = data.selectedDoc;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedZaposleni.radnoMesto = selectedMesto;
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
