'use strict';

 angular.module('modal',[])

 .controller('genericModalCtrl', function ($scope, $modal, $modalInstance) {

	$scope.sacuvaj = function() {
		$modalInstance.close({'item':$scope.item, 
							  'action':'sacuvaj'});
	}
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
});

