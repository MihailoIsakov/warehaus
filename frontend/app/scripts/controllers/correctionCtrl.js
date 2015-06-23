'use strict';

angular.module('correction', ['resource.artikale', 'resource.magacinska'])

.controller('correctionCtrl', function (Artikale, $scope, Kartica, $location) {
	$scope.articled = Artikale.findByMagCardId({'id': 1});
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
		}
		$scope.articled.pocetnoStanjeKol = temp.pocetnoStanjeKol + a;
		$scope.articled.vrUlaza = temp.vrUlaza + (a * temp.prosecnaCena);
		Kartica.update($scope.articled);
		var magacin = parseInt($scope.articled.magacin.idMagacin);
		$location.path('/lager-list/'+magacin);
	};
	
	$scope.cancel = function () {
		$location.path('/lager-list/'+magacin);
	};
});