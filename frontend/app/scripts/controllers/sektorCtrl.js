'use strict';

 angular.module('sektor', ['resource.sektor',
 	'angular-md5'])

 .controller('sektorCtrl', function (Sektor, $scope, $routeParams, $modal, $log, $location, InvoiceItem ,$route ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Sektor.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.sektorDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.sektorDoc = new Sektor();
		$scope.sektorDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedSektor = new Sektor();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-sektor.html',
			controller: 'sektorNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedSektor = data.selectedSektor;
			
				if( data.action==='save'){
				selectedSektor.$create(function () {
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
	


	$scope.update = function (invoiceItem, size) {

		if($scope.selectedSektor){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-sektor.html',
			controller: 'sektorNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedSektor = data.selectedSektor;
			
				if( data.action==='save'){
				selectedSektor.$update({invoiceItemId:$scope.selectedSektor}, function () {
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
		}
	};
	





	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		Sektor.delete({invoiceItemId:$scope.selectedSektor.idSektor},function () {
				$route.reload();
			});
	}


$scope.setSelected = function (selectedSektor) {
   $scope.selectedSektor = selectedSektor;
};

$scope.sektorDoc = "";
	$scope.options = Sektor.query();
	$log.info($scope.sektorDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

