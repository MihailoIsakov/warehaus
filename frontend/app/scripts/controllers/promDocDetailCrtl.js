'use strict';

 angular.module('modal',[])

 .controller('promDocDetailCtrl', function ($scope, $modalInstance, StavkaPD, VrstaPD, Magacin) {

 //vrste prometnih dokumenata za drop down
	VrstaPD.query().$promise.then(function (data) {
			$scope.vrste = data;
	});
	
	 //magacini za drop down
	Magacin.query().$promise.then(function (data) {
			$scope.magacini = data;
	});
	
	$scope.sacuvaj = function() {
		$modalInstance.close({'item':$scope.item, 
							  'action':'sacuvaj'});
	}
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
});

