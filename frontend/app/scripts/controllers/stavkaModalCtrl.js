'use strict';

 angular.module('stavkaModal',[])

 .controller('stavkaModalCtrl', function ($scope, $modal, $modalInstance) {

	$scope.sacuvaj = function() {
		$scope.selectedStavka.vrednostStavke = parseInt($scope.selectedStavka.cenaStavke)*parseInt($scope.selectedStavka.kolicinaPrDokumenta);
		$modalInstance.close({'selectedStavka':$scope.selectedStavka, 
							  'action':'save'});
	}
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
	
		$scope.selektujArtikal = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/artikli-modal.html',
			controller: 'artikliModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedArtikal = data.selectedArtikal;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save') {
					$scope.selectedStavka.artikal = selectedArtikal;
				}
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
		}
});

