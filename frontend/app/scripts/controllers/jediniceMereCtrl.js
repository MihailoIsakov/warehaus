'use strict';

 angular.module('jediniceMere', ['angular-md5'])

 .controller('jediniceMereCtrl', function (JedinicaMere, $scope, $routeParams,$route, $modal, $log, $location ) {

if($routeParams.jedinicaMereId !='new'){
		//preuzimanje parametra iz URL
		var jedinicaMereId = $routeParams.jedinicaMereId;
		
		JedinicaMere.query({'idJedinicaMere':jedinicaMereId}).$promise.then(function (data) {
			$scope.jediniceMereDoc = data;
		});
	}

	else{
		$scope.jediniceMereDoc = new JedinicaMere();
}


	//modalni dijalog za stavku fakutre
	$scope.add = function () {		
		
		$scope.selectedJedinicaMere = new JedinicaMere();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-jedinicu-mere.html',
			controller: 'jedinicaMereNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedJedinicaMere = data.selectedJedinicaMere;
			
			if( data.action==='save'){
				selectedJedinicaMere.$create(function () {
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

		if($scope.selectedJedinicaMere){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-jedinicu-mere.html',
			controller: 'jedinicaMereNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var selectedJedinicaMere = data.selectedJedinicaMere;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedJedinicaMere.$update({idJedinicaMere:$scope.selectedJedinicaMere}, function () {
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

		JedinicaMere.delete({idJedinicaMere:$scope.selectedJedinicaMere.idJedinicaMere},function () {
			$route.reload();
			});
	}

$scope.setSelected = function (selectedJedinicaMere) {
   $scope.selectedJedinicaMere = selectedJedinicaMere;
};

});

