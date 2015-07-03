'use strict';

 angular.module('preduzecaM', ['resource.preduzeca',
 	'angular-md5'])

 .controller('preduzecaModalCtrl', function (Preduzeca, $scope, $routeParams, $modal, $modalInstance, $log, $location, InvoiceItem ,$route) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Preduzeca.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.preduzDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.preduzDoc = new Preduzeca();
		$scope.preduzDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedPreduzece = new Preduzeca();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-preduzece.html',
			controller: 'preduzeceNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
		var selectedPreduzece = data.selectedPreduzece;
			
				if( data.action==='save'){
				selectedPreduzece.$create(function () {
					$scope.preduzDoc.push(selectedPreduzece);
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

		if($scope.selectedPreduzece){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-preduzece.html',
			controller: 'preduzeceNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedPreduzece = data.selectedPreduzece;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedPreduzece.$update({invoiceItemId:$scope.selectedPreduzece}, function () {
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

		Preduzeca.delete({invoiceItemId:$scope.selectedPreduzece.idPreduzece},function () {
				$route.reload();
			});
	}

$scope.selektuj = function () {

			
		$modalInstance.close({'selectedPreduzece':$scope.selectedPreduzece,
								'action':'save'});
	
	}
$scope.setSelected = function (selectedPreduzece) {
   $scope.selectedPreduzece = selectedPreduzece;
};

$scope.preduzDoc = "";
	$scope.options = Preduzeca.query();
	$log.info($scope.preduzDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

