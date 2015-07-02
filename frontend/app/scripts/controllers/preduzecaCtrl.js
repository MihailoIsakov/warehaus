'use strict';

 angular.module('preduzeca', ['resource.preduzeca',
 	'angular-md5'])

 .controller('preduzecaCtrl', function (Preduzeca, $scope, $routeParams, $modal, $log, $location, InvoiceItem ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Preduzeca.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.promDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.promDoc = new Preduzeca();
		$scope.promDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {


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
				selectedDoc.$create(function () {
				$location.path('/prometni-dokumenti');

			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
               
            }
		);
				$location.path('/prometni-dokumenti');
					
			}
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
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
	$scope.options = Preduzeca.query();
	$log.info($scope.promDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

