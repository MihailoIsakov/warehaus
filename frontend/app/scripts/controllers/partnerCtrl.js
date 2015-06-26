'use strict';

 angular.module('partner', ['resource.partner',
 	'angular-md5'])

 .controller('partnerCtrl', function (Partner, $scope, $routeParams, $modal, $log, $location) {

$scope.setSelected = function (selectedPartner) {
   $scope.selectedPartner = selectedPartner;
};

	$scope.partners = Partner.query();
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	$scope.insertOrEditPartner = function (idPartnera) {
 		if(idPartnera){
 			$location.path('/poslovni-partner/'+idPartnera);
 		}
 		else{
			$location.path('/poslovni-partner/new');
 		}
 	}
});

