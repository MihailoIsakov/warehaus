'use strict';

 angular.module('magacini', ['angular-md5'])

 .controller('magaciniCtrl', function (Magacin, $scope, $routeParams,$route, $modal, $log, $location ) {

if($routeParams.magacinId!='new'){
		//preuzimanje parametra iz URL
		var magacinId = $routeParams.magacinId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Magacin.query({'magacinId':magacinId}).$promise.then(function (data) {
			$scope.magaciniDoc = data;
		});
	}

	//ako kreiramo novi
	else{
		$scope.magaciniDoc = new Magacin();
	
}


	//modalni dijalog za stavku fakutre
	$scope.add = function () {
		
		$scope.selectedMagacin = new Magacin();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-magacin.html',
			controller: 'magacinNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedMagacin = data.selectedMagacin;
			
			if( data.action==='save'){
				selectedMagacin.$create(function () {
					$scope.magaciniDoc.push(selectedMagacin);
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
	
	$scope.update = function () {

		if($scope.selectedMagacin){
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-magacin.html',
			controller: 'magacinNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var selectedMagacin = data.selectedMagacin;
			
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if( data.action==='save'){
				selectedMagacin.$update({magacinId:$scope.selectedMagacin}, function () {
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

		Magacin.delete({magacinId:$scope.selectedMagacin.idMagacin},function () {
				$route.reload();
			});
	}

$scope.setSelected = function (selectedMagacin) {
   $scope.selectedMagacin = selectedMagacin;
};


$scope.magaciniDoc = "";
	$scope.options = Magacin.query();
});

