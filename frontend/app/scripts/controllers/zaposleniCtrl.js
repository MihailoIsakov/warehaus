'use strict';

 angular.module('zaposleni', ['resource.zaposleni',
 	'angular-md5'])

 .controller('zaposleniCtrl', function (Zaposleni, $scope, $routeParams, $modal, $log, $location, InvoiceItem ,$route ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Zaposleni.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.zaposleniDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.zaposleniDoc = new Zaposleni();
		$scope.zaposleniDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedZaposleni = new Zaposleni();
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
				selectedZaposleni.$create(function () {
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

	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		Zaposleni.delete({invoiceItemId:$scope.selectedZaposleni.idZaposleni},function () {
				$route.reload();
			});
	}


$scope.setSelected = function (selectedZaposleni) {
   $scope.selectedZaposleni = selectedZaposleni;
};

$scope.zaposleniDoc = "";
	$scope.options = Zaposleni.query();
	$log.info($scope.zaposleniDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

