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

		$scope.item = new StavkaPD();
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'genericModalCtrl',
			size: size,
			scope: $scope,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var stavka = data.item;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='sacuvaj'){
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
			$scope.item = $scope.selectedStavka;
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'genericModalCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var stavka = data.item;
			
			if( data.action==='sacuvaj'){
				stavka.$update({stavkaId:$scope.item}, function () {
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

 	$scope.insertOrEditStavka = function (idStavke) {
 		if(idStavke){
 			$location.path('/stavkaPD/'+idStavke);
 		}
 		else{
			$location.path('/stavkaPD/new');
 		}
 	}
});

