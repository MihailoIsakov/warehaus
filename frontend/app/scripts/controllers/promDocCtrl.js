'use strict';

 angular.module('promDoc', ['resource.promDoc',
 	'angular-md5'])

 .controller('promDocCtrl', function (Documents, $scope, $routeParams, $modal, $route, $log, $location ) {
	
if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Documents.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.promDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.promDoc = new Documents();
		$scope.promDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.storniranje = function (invoiceItem, size) {

		var modalInstance = $modal.open({
			templateUrl: 'views/storniraj-primku.html',
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
				selectedDoc.$update(function () {
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
	$scope.dodavanje = function (size) {
	
		$scope.item = new Documents();
		var modalInstance = $modal.open({
			templateUrl: 'views/promDocDetail.html',
			controller: 'promDocDetailCtrl',
			size: size,
			scope: $scope,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var item = data.item;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				item.$create(function () {
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
	$scope.pregled = function (size) {
	
		$scope.item = $scope.selectedDoc;
		var modalInstance = $modal.open({
			templateUrl: 'views/pregledPD.html',
			controller: 'promDocDetailCtrl',
			size: size,
			scope: $scope,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});
	};
	

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};

$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
$scope.promDoc = "";
	$scope.options = Documents.query();
	$log.info($scope.promDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

