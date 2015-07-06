'use strict';

angular.module('correction', ['resource.artikale', 'resource.magacinska'])

.controller('correctionCtrl', function (Artikale, $scope, Kartica, $routeParams,$location) {
	$scope.articled = Artikale.findByMagCardId({'id': $routeParams.karticaId});
	$scope.isVisible = false;
	$scope.error = "";
	
	$scope.addCor = function (newValue) {
		var temp = $scope.articled;
		var a = isNaN(parseInt($scope.newQuantity, 10)) ? 0 : parseInt($scope.newQuantity, 10);
		if(a === 0){
			$scope.isVisible = true;
			$scope.error = "Nije validan unos. Morate uneti broj.";
		} 
		else {
			$scope.isVisible = false;
			$scope.error = "";
			$scope.articled.kolUlaza = temp.kolUlaza + a;
			$scope.articled.vrUlaza = temp.vrUlaza + (a * temp.prosecnaCena);
			Kartica.update($scope.articled);
			var magacin = parseInt($scope.articled.magacin.idMagacin);
			window.location = '#/lager-list/' + magacin;
			window.location.reload();
		}
	};
	
	$scope.cancel = function () {
        window.location = '#/lager-list/' + magacin;
	};
});