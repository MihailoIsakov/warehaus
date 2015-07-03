'use strict';

 angular.module('promDocDetail',[])

 .controller('promDocDetailCtrl', function ($scope, $modal, $modalInstance, StavkaPD, VrstaPD, Magacin, PoslovnaGodina) {

 //vrste prometnih dokumenata za drop down
	VrstaPD.query().$promise.then(function (data) {
			$scope.vrste = data;
	});
	
	 //magacini za drop down
	Magacin.query().$promise.then(function (data) {
			$scope.magacini = data;
			var magNull = new Magacin();
			magNull.nazivMagacina = "";
			$scope.magacini.push(magNull);
	});
	
	//poslovneGodine za drop down
	PoslovnaGodina.query().$promise.then(function (data) {
			$scope.poslovneGodine = data;
	});
	$scope.sacuvaj = function() {
		$modalInstance.close({'item':$scope.item, 
							  'action':'sacuvaj'});
	}
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
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
	
	//otvaranje modala
	$scope.odabirPoslovnogPartnera = function(size) {
		
		var modalInstance = $modal.open({
			templateUrl: 'views/poslovniPartnerModal.html',
			controller: 'partnerModalCtrl',
			scope: $scope,
			resolve: {
				partner: function () {
					return $scope.selectedPartner;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var partner = data.partner;
			
			if( data.action==='odabir')
				$scope.item.poslovniPartner = partner;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
		
	$scope.sacuvaj = function () {
		$modalInstance.close({'item':$scope.item,
								'action':'sacuvaj'});
	};

	$scope.odustani = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.dodajStavkuPD = function() {
		
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'stavkaPDCtrl',
			scope: $scope,
			resolve: {
				stavka: function () {
					return $scope.item;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var stavka = data.stavka;
			
			if( data.action==='sacuvaj')
				$scope.item.stavke.push(stavka);
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			//ako stavka treba da se obrise izbaci se iz niza
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
	
	}
	
);

