'use strict';

 angular.module('stavkaPopisa',[])

 .controller('stavkaPopisaNewCtrl', function ($scope, $modal, $modalInstance) {

	$scope.sacuvaj = function() {
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

