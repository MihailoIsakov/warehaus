'use strict';

 angular.module('poslGodina', ['resource.poslGodina',
 	'angular-md5'])

 .controller('poslGodinaCtrl', function (PoslovnaGodina,PoslGodinaDoc, $scope, $routeParams, $modal, $log, $location, InvoiceItem ,$route ) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		PoslovnaGodina.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.promDoc = data;
		});
	}

	//ako kreiramo novu fakutru
	else{
		$scope.promDoc = new PoslovnaGodina();
		$scope.promDoc.invoiceItems = [];
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function (invoiceItem, size) {

		$scope.selectedPoslGod = new PoslovnaGodina();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-poslgod.html',
			controller: 'poslgodNewCtrl',
			size: size,
			scope: $scope ,
			resolve: {
				invoiceItem: function () {
					return $scope.invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var selectedPoslGod = data.selectedPoslGod;
			
				if( data.action==='save'){
				selectedPoslGod.$create(function () {
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

			PoslGodinaDoc.get({invoiceItemId:$scope.selectedPoslGod.idPoslovnaGodina},function (data) {
					$scope.returnList = data;
					
					if(data.length!=0){
						var modalInstance = $modal.open({
							templateUrl: 'views/neproknjizeni-dokumenti.html',
							controller: 'promDocModalCtrl',
							size: size,
							scope: $scope,
							 windowClass: 'app-modal-window'
						});
						modalInstance.result.then(function (data) {
							var selectedPoslGod = data.selectedPoslGod;
			
							if( data.action==='save'){
								selectedPoslGod.$create(function () {
								$route.reload();
							},
            				function (response) {
                				if (response.status === 500) {
                    			$scope.greska = "greska";
               		 		}
               
           			 	});
					$route.reload();		
				}
				else if(data.action==='refresh'){
						$scope.update();
				}
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
					}
					else{
						$route.reload();
					}
			});
	};

	//modalni dijalog za stavku fakutre
	$scope.delete = function () {

		PoslovnaGodina.delete({invoiceItemId:$scope.selectedPoslGod.idPoslovnaGodina},function () {
				$route.reload();
			});
	}


$scope.setSelected = function (selectedPoslGod) {
   $scope.selectedPoslGod = selectedPoslGod;
};

$scope.promDoc = "";
	$scope.options = PoslovnaGodina.query();
	$log.info($scope.promDoc.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	
});

