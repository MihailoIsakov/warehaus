'use strict';

 angular.module('grupeArtikala', ['angular-md5'])

 .controller('grupeArtikalaCtrl', function (GrupaArtikala, $scope, $routeParams,$route, $modal, $log, $location ) {

if($routeParams.grupaArtikalaId !='new'){
		//preuzimanje parametra iz URL
		var grupaArtikalaId = $routeParams.grupaArtikalaId;
		
		GrupaArtikala.query({'idGrupaArtikala':grupaArtikalaId}).$promise.then(function (data) {
			$scope.grupeArtikalaDoc = data;
		});
	}

	else{
		$scope.grupeArtikalaDoc = new GrupaArtikala();
}


	//modalni dijalog za stavku fakutre
	$scope.add = function () {		
		
		$scope.selectedGrupaArtikala = new GrupaArtikala();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-grupu-artikala.html',
			controller: 'grupaArtikalaNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedGrupaArtikala = data.selectedGrupaArtikala;
			
			if( data.action==='save'){
				selectedGrupaArtikala.$create(function () {
					$route.reload();
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
               
            }
		);
			$route.reload();
					
			}
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	$scope.update = function () {

		if($scope.selectedGrupaArtikala){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-grupu-artikala.html',
			controller: 'grupaArtikalaNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var selectedGrupaArtikala = data.selectedGrupaArtikala;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedGrupaArtikala.$update({idGrupaArtikala:$scope.selectedGrupaArtikala}, function () {
				$route.reload();

			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
               
            }
		);
				$route.reload();
					
			}
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
	};

	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		GrupaArtikala.delete({idGrupaArtikala:$scope.selectedGrupaArtikala.idGrupaArtikala},function () {
			$route.reload();
			});
	}

$scope.setSelected = function (selectedGrupaArtikala) {
   $scope.selectedGrupaArtikala = selectedGrupaArtikala;
};


	$scope.grupeArtikalaDoc = "";
	$scope.options = GrupaArtikala.query();
});

