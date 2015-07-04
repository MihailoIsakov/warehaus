'use strict';

 angular.module('artikli', ['angular-md5'])

 .controller('artikliCtrl', function (PraviArtikal, $scope, $routeParams,$route, $modal, $log, $location ) {

if($routeParams.artikalId !='new'){
		//preuzimanje parametra iz URL
		var artikalId = $routeParams.artikalId;
		
		PraviArtikal.query({'idArtikla':artikalId}).$promise.then(function (data) {
			$scope.artikliDoc = data;
		});
	}

	else{
		$scope.artikliDoc = new PraviArtikal();
}


	//modalni dijalog za stavku fakutre
	$scope.add = function () {		
		
		$scope.selectedArtikal = new PraviArtikal();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-artikal.html',
			controller: 'artikalNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedArtikal = data.selectedArtikal;
			
			if( data.action==='save'){
				selectedArtikal.$create(function () {
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
	
	$scope.update = function () {

		if($scope.selectedArtikal){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-artikal.html',
			controller: 'artikalNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var selectedArtikal = data.selectedArtikal;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedArtikal.$update({idArtikla:$scope.selectedArtikal}, function () {
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

		PraviArtikal.delete({'idArtikal':$scope.selectedArtikal.idArtikal},function () {
			$route.reload();
			});
	}

$scope.setSelected = function (selectedArtikal) {
   $scope.selectedArtikal = selectedArtikal;
};


	$scope.grupeArtikalaDoc = "";
	$scope.options = PraviArtikal.query();
});

