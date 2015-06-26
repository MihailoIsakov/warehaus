'use strict';

angular.module('primka', ['resource.primka'])

.controller('primkaCtrl', function (PrometniDokument, $scope, $routeParams, $log, $location) {
	
	if($routeParams.primkaId!='new'){
		//preuzimanje parametra iz URL
		var primkaId = $routeParams.primkaId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		PrometniDokument.query({'primkaId':primkaId}).$promise.then(function (data) {
			$scope.primka = data;
		});
	}
	//ako kreiramo novu fakutru
	else{
		$scope.primka = new PrometniDokument();
	}
	
	//funkcija koja otvara datepicker
	$scope.openDatumKnjizenjaDatepicker = function($event, datumKnjizenjaOpened) {
		$scope['datumNastankaOpened'] = false;
		$event.preventDefault();
		$event.stopPropagation();
		$scope[datumKnjizenjaOpened] = true;
	};
	
	//funkcija koja otvara datepicker
	$scope.openDatumNastankaDatepicker = function($event, datumNastankaOpened) {
		$scope['datumKnjizenjaOpened'] = false;
		$event.preventDefault();
		$event.stopPropagation();
		$scope[datumNastankaOpened] = true;
	};
	
	//modalni dijalog za stavku fakutre
	$scope.openPartnersModal = function (partner, size) {

		var modalInstance = $modal.open({
			templateUrl: 'views/poslovni-partner.html',
			controller: 'partnersCtrl',
			size: size,
			resolve: {
				partner: function () {
					return partner;
				}
			}
		});
		modalInstance.result.then(function (data) {
			$scope.primka.partner = data.partner;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	//cuvanje izmena
	$scope.save = function () {
		if($scope.primka.id){
			//zbog cega redirekcija ide na callback?
			$scope.primka.$update({primkaId:$scope.primka.id},function () {
				$location.path('/');
			});
		}
		else{
			$scope.primka.$save(function () {
				$location.path('/');
			});
		}
		$log.info("save");
	}

	$scope.delete = function () {
		if($scope.primka.id){
			$scope.primka.$delete({primkaId:$scope.primka.id}, function () {
				$location.path('/');
			});
		}
	}
});