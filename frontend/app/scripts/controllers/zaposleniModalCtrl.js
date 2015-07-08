'use strict';

 angular.module('zaposleniModal', ['resource.zaposleni',
 	'angular-md5'])

 .controller('zaposleniModalCtrl', function (Zaposleni, $scope, $routeParams, $modal, $modalInstance, $log, $location, InvoiceItem ,$route ) {

	Zaposleni.query().$promise.then(function (data) {
			$scope.zaposleniDoc = data;
	});

	//modalni dijalog za stavku fakutre
	$scope.add = function (size) {

		$scope.selectedZaposleni = new Zaposleni();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-zaposleni.html',
			controller: 'zaposleniNewCtrl',
			size:'size',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var selectedZaposleni = data.selectedZaposleni;
			
				if( data.action==='save'){
				selectedZaposleni.$create(function () {
					$scope.zaposleniDoc.push(selectedZaposleni);
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
	
	$scope.update = function (invoiceItem, size) {	
		
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-zaposleni.html',
			controller: 'zaposleniNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedZaposleni = data.selectedZaposleni;
			
				if( data.action==='save'){
				selectedZaposleni.$update({invoiceItemId:$scope.selectedZaposleni}, function () {
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

	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		Zaposleni.delete({invoiceItemId:$scope.selectedZaposleni.idZaposleni},function () {
				$scope.zaposleniDoc.splice($scope.zaposleniDoc.indexOf($scope.selectedZaposleni),1);
			});
	}

	$scope.selektuj = function () {
			
		$modalInstance.close({'selectedZaposleni':$scope.selectedZaposleni,
								'action':'save'});
	}

$scope.setSelected = function (selectedZaposleni) {
   $scope.selectedZaposleni = selectedZaposleni;
};

});

