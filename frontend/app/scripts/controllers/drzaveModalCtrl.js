'use strict';

 angular.module('drzaveM', ['resource.drzave',
 	'angular-md5'])

 .controller('drzaveModalCtrl', function (Drzave, $scope, $routeParams,$route, $modal, $modalInstance, $log, $location, InvoiceItem ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Drzave.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.drzaveDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.drzaveDoc = new Drzave();
		$scope.drzaveDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedDoc = new Drzave();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-drzavu.html',
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
				$scope.drzaveDoc.push(selectedDoc);

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
			templateUrl: 'views/dodaj-drzavu.html',
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

		Drzave.delete({invoiceItemId:$scope.selectedDoc.idDrzava},function () {
				$scope.drzaveDoc.splice($scope.drzaveDoc.indexOf($scope.selectedDoc),1);
			});
	}

	$scope.selektuj = function () {

			
		$modalInstance.close({'selectedDoc':$scope.selectedDoc,
								'action':'save'});
	
	}

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};


$scope.drzaveDoc = "";
	$scope.options = Drzave.query();
	$log.info($scope.drzaveDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

