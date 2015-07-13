'use strict';

 angular.module('popisniDokumenti', ['resource.popisniDokument',
 	'angular-md5'])

 .controller('popisniDokumentiCtrl', function (PopisniDokument, $scope, $routeParams, $modal, $route, $log, $location ) {
	
	PopisniDokument.query({'id':''}).$promise.then(function (data) {
		$scope.dokumenti = data;
	});

	$scope.proknjizi = function () {
			$scope.selectedDoc.$update({'id':'knjizenje'},function () {
				$route.reload();
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
               
            })
			}

/*
	$scope.storniranje = function () {

		if($scope.selectedDoc && $scope.selectedDoc.idPrometniDokument){
		var modalInstance = $modal.open({
			templateUrl: 'views/storniraj-popisni-dokument.html',
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
*/

	$scope.dodavanje = function () {
	
		$scope.selectedDoc = new PopisniDokument();
		$scope.selectedDoc.stavke = [];
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-popisni-dokument.html',
			controller: 'popisniDokumentCtrl',
			size:'lg',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var doc = data.selectedDoc;
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
	$scope.pregled = function () {
		if($scope.selectedDoc && $scope.selectedDoc.idPopisniDokument){
		var modalInstance = $modal.open({
			templateUrl: 'views/pregled-popisnog-dokumenta.html',
			controller: 'popisniDokumentCtrl',
			size:'lg',
			scope: $scope
		});
	}
	};	
	
	$scope.stampaj = function() {
		
	}
	
	$scope.unesiPopisaneStavke = function() {
		if ($scope.selectedDoc.statusPredaje!="proknjizen") {
			
			var modalInstance = $modal.open({
				templateUrl: 'views/unos-popisnog-dokumenta.html',
				controller: 'popisniDokumentCtrl',
				size:'lg',
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
			}, function () {
				$log.info('Modal dismissed at: ' + new Date());
			});
		}
	}
	
	$scope.izmeni = function () {
		if ($scope.selectedDoc.statusPredaje!="proknjizen") {
			
			var modalInstance = $modal.open({
				templateUrl: 'views/izmeni-popisni-dokument.html',
				controller: 'popisniDokumentCtrl',
				size:'lg',
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
});

