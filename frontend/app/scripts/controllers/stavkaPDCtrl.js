'use strict';

 angular.module('stavkaPD', ['resource.stavkaPD',
 	'angular-md5'])

 .controller('stavkaPDCtrl', function (StavkaPD, $scope, $routeParams, $modal, $log, $location) {

$scope.setSelected = function (selectedStavka) {
   $scope.selectedStavka = selectedStavka;
};

	$scope.stavke = StavkaPD.query().$promise.then(function(data) {
		$scope.stavke = data;
		}, function(error) {
			console.log(error);
		}); 
	
	$scope.dodavanje = function (stavka, size) {

		$scope.selectedStavka = new StavkaPD();
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'genericModalCtrl',
			size: size,
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var stavka = data.selectedStavka;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				stavka.$create(function () {
									$route.reload();
								},
							   function (response) {
									if (response.status === 500) {
										$scope.greska = "greska";
									} 
								}
				);	
			}
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	$scope.izmena = function (stavka, size) {

		if($scope.selectedStavka){
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'genericModalCtrl',
			size: size,
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var stavka = data.selectedStavka;
			
			if( data.action==='save'){
				stavka.$update({stavkaId:$scope.selectedStavka}, function () {
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
	
	$scope.brisanje = function () {

	StavkaPD.delete({stavkaId:$scope.selectedStavka.idStavke},function () {
			$route.reload();
		},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
            }
		);
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
					selectedArtikal.vrednost = selectedArtikal.cena*selectedArtikal.kolicina;
					$scope.item.artikal = selectedArtikal;
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
		
 	$scope.insertOrEditStavka = function (idStavke) {
 		if(idStavke){
 			$location.path('/stavkaPD/'+idStavke);
 		}
 		else{
			$location.path('/stavkaPD/new');
 		}
 	}
});

