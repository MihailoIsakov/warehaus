'use strict';

 angular.module('radnoMestoM', ['resource.radnoMesto',
 	'angular-md5'])

 .controller('radnoMestoModalCtrl', function (RadnoMesto, $scope, $routeParams,$route, $modal, $modalInstance, $log, $location, InvoiceItem ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		RadnoMesto.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.radnoMestoDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.RadnoMestoDoc = new RadnoMesto();
		$scope.RadnoMestoDoc.invoiceItems = [];
	
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
				$scope.radnoMestoDoc.push(selectedDoc);

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
		});}
	};

	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		RadnoMesto.delete({invoiceItemId:$scope.selectedDoc.idRadnoMesto},function () {
				$scope.radnoMestoDoc.splice($scope.radnoMestoDoc.indexOf($scope.selectedDoc),1);
			});
	}

	$scope.selektuj = function () {

			
		$modalInstance.close({'selectedDoc':$scope.selectedDoc,
								'action':'save'});
	
	}

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};


$scope.RadnoMestoDoc = "";
	$scope.options = RadnoMesto.query();
	$log.info($scope.RadnoMestoDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

