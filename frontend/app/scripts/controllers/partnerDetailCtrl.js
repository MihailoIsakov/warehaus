'use strict';

 angular.module('modal',[])

 .controller('partnerDetailCtrl', function ($scope, $modal, $modalInstance) {

	//vrste partnera za drop down
	$scope.vrste = ["kupac","dobavljac","kupac i dobavljac"];
	
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
				$scope.item.mesto = selectedMesto;
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
		
	$scope.sacuvaj = function() {
		$modalInstance.close({'item':$scope.item, 
							  'action':'save'});
	}
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
});

