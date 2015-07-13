'use strict';

 angular.module('promDoc2', ['resource.promDoc',
 'resource.magCardpga',
 	'angular-md5'])

 .controller('promDocModalCtrl', function (Documents, Pga, $scope, $routeParams, $modalInstance, $modal, $route, $log, $location ) {
	
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


$scope.proknjizi2 = function (invoiceItem, size) {
	$scope.greskaf = $scope.selectedDoc;
	var i=0;
	for(i=0;i<$scope.promDoc.length;i++){
		$scope.greskaf = $scope.promDoc[i].idPrometniDokument;
		if($scope.promDoc[i].idPrometniDokument==$scope.selectedDoc.idPrometniDokument){
			
			if($scope.selectedDoc.vrstaDokumenta.nazivVrste !== "primka"){
				var len = $scope.selectedDoc.stavke.length;
				for(var i=0; i < len; i++){
					var objekat = $scope.selectedDoc.stavke[i];
					var idArtikal = $scope.selectedDoc.stavke[i].artikal.idArtikal;
					var idMagacin = $scope.selectedDoc.magacin2.idMagacin;
					var idPG = $scope.selectedDoc.poslovnaGodina.idPoslovnaGodina;
					Pga.findGG({'idArtikal':idArtikal, 'idMagacin':idMagacin, 'idPG':idPG}).$promise.then(function (data) {
						$scope.gkg = data;
						if($scope.gkg === null){
							alert("Artikla "+objekat.artikal.nazivArtikla+" nema u magacinu.");
							ok = 0;
						}
						else{
							var ukupnaVr = $scope.gkg.pocetnoStanjeVr + $scope.gkg.vrUlaza - $scope.gkg.vrIzlaza;
							var ukupnaKol = $scope.gkg.pocetnoStanjeKol + $scope.gkg.kolUlaza - $scope.gkg.kolIzlaza;
							if(ukupnaKol < objekat.kolicinaPrDokumenta){
								alert("Artikla " + objekat.artikal.nazivArtikla + 
								" nema dovoljno na stanju u magacinu.");
								ok = 0;
							}
						}
					});
				}
			}
			if(ok === 1){
				$scope.promDoc[i].$update({'id':'knjizenje'},function () {
					$modalInstance.close({'invoiceItem':$scope.invoiceItem,
									'action':'refresh'});
				},
				function (response) {
					$modalInstance.close({'invoiceItem':$scope.invoiceItem,
									'action':'refresh'});
					if (response.status === 500) {
						$scope.greska = "greska";
					}
					
				})
			}
		}
	}
}

$scope.proknjizi = function (invoiceItem, size) {
			$scope.selectedDoc.$update({'id':'knjizenje'},function () {
				$route.reload();
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
               
            })
			}




	$scope.storniranje = function (invoiceItem, size) {

		if($scope.selectedDoc && $scope.selectedDoc.idPrometniDokument){
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
				selectedDoc.$update({'id':'storniranje'},function () {
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
	$scope.dodavanje = function (size) {
	
		$scope.selectedDoc = new Documents();
		$scope.selectedDoc.stavke = [];
		var modalInstance = $modal.open({
			templateUrl: 'views/promDocDetail.html',
			controller: 'promDocDetailCtrl',
			size: size,
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var doc = data.selectedDoc;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
					if( data.action==='save'){
				doc.$create(function () {
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
		if($scope.selectedDoc && $scope.selectedDoc.idPrometniDokument){
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
	}
	};
	
	$scope.izmeni = function () {
		if ($scope.selectedDoc.statusDokumenta!="proknjizen") {
			
			var modalInstance = $modal.open({
				templateUrl: 'views/promDocDetail.html',
				controller: 'promDocDetailCtrl',
				scope: $scope
			});
			modalInstance.result.then(function (data) {
				var selectedDoc = data.selectedDoc;
				
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
		}
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

