'use strict';

 angular.module('radnoMesto', ['resource.radnoMesto',
 	'angular-md5'])

 .controller('radnoMestoCtrl', function (RadnoMesto, $scope, $routeParams,$route, $modal,  $log, $location, InvoiceItem ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		RadnoMesto.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.greska = "ffff2";
			$scope.radnoMestoDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.greska = "ffff";
		$scope.radnoMestoDoc = new RadnoMesto();
		$scope.radnoMestoDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedDoc = new RadnoMesto();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-radmesto.html',
			controller: 'stonPrimCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedDoc = data.selectedDoc;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedDoc.$create(function () {
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

		if($scope.selectedDoc){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-radmesto.html',
			controller: 'stonPrimCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedDoc = data.selectedDoc;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedDoc.$update({invoiceItemId:$scope.selectedDoc}, function () {
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

		RadnoMesto.delete({invoiceItemId:$scope.selectedDoc.idRadnoMesto},function () {
				$route.reload();
			});
	}

	$scope.selektuj = function () {

			
		$modalInstance.close({'selectedDoc':$scope.selectedDoc,
								'action':'save'});
	
	}

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};


$scope.radnoMestoDoc = "";
	$scope.options = RadnoMesto.query();
	$log.info($scope.radnoMestoDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

