'use strict';

 angular.module('mestaM', ['resource.mesta',
 	'angular-md5'])

 .controller('mestaModalCtrl', function (Mesta, $scope, $routeParams,$route, $modalInstance, $modal, $log, $location, InvoiceItem ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Mesta.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.mestaDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.mestaDoc = new Mesta();
		$scope.mestaDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		
		
		$scope.selectedMesto = new Mesta();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-mesto.html',
			controller: 'mestoNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			
			var selectedMesto = data.selectedMesto;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedMesto.$create(function () {
					$scope.mestaDoc.push(selectedMesto);
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

		if($scope.selectedMesto){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-mesto.html',
			controller: 'mestoNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedMesto = data.selectedMesto;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedMesto.$update({invoiceItemId:$scope.selectedMesto}, function () {
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

		Mesta.delete({invoiceItemId:$scope.selectedMesto.idMesto},function () {
				$route.reload();
			});
	}

$scope.selektuj = function () {

			
		$modalInstance.close({'selectedMesto':$scope.selectedMesto,
								'action':'save'});
	
	}
$scope.setSelected = function (selectedMesto) {
   $scope.selectedMesto = selectedMesto;
};


$scope.mestaDoc = "";
	$scope.options = Mesta.query();
	$log.info($scope.mestaDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

