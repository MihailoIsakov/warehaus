'use strict';

 angular.module('partner', ['resource.partner',
 	'angular-md5'])

 .controller('partnerCtrl', function (Partner, $scope, $routeParams, $modal, $log, $location, $route) {

$scope.setSelected = function (selectedPartner) {
   $scope.selectedPartner = selectedPartner;
};

	$scope.partners = Partner.query().$promise.then(function(data) {
		$scope.partners = data;
		}, function(error) {
			console.log(error);
		}); 
	
	$scope.dodavanje = function (partner, size) {

		$scope.item = new Partner();
		var modalInstance = $modal.open({
			templateUrl: 'views/partnerDetail.html',
			controller: 'partnerDetailCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var partner = data.item;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				partner.$create(function () {
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
	
	$scope.izmena = function (partner, size) {

		if($scope.selectedPartner){
			$scope.item = $scope.selectedPartner;
		var modalInstance = $modal.open({
			templateUrl: 'views/partnerDetail.html',
			controller: 'partnerDetailCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var partner = data.item;
			
			if( data.action==='save'){
				partner.$update({partnerId:$scope.item}, function () {
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

	Partner.delete({'partnerId':$scope.selectedPartner.idPoslovniPartner},function () {
			$route.reload();
		},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
            }
		);
	}

	
 	$scope.insertOrEditPartner = function (idPartnera) {
 		if(idPartnera){
 			$location.path('/poslovni-partner/'+idPartnera);
 		}
 		else{
			$location.path('/poslovni-partner/new');
 		}
 	}
});

