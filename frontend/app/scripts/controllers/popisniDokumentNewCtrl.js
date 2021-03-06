﻿'use strict';

 angular.module('popisniDokument',['ui.bootstrap'])

 .controller('popisniDokumentCtrl', function ($scope, $modal, $modalInstance, Magacin, PoslovnaGodina, StavkaPopisnogDokumenta, datepickerPopupConfig) {
	
	$scope.today = new Date();
	datepickerPopupConfig.currentText = "Danas";
	datepickerPopupConfig.clearText="Obriši";
	$scope.show = false;
	$scope.vrstaIndex = 0;
	$scope.godinaIndex = 0;
	if (!$scope.selectedDoc.datumPopisa) {
		$scope.selectedDoc.datumPopisa = new Date();
	}
	
	//poslovneGodine za drop down
	PoslovnaGodina.query().$promise.then(function (data) {
			$scope.poslovneGodine = data;
			$scope.godinaIndex = $scope.poslovneGodine.indexOf($scope.selectedDoc.poslovnaGodina);
	});
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
	
	//funkcija koja otvara datepicker
	$scope.openDatumPopisaDatepicker = function($event, datumPopisaOpened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[datumPopisaOpened] = true;
	};
	
	//otvaranje modala
	$scope.odabirPopisneKomisije = function() {
		
		var modalInstance = $modal.open({
			templateUrl: 'views/popisne-komisije-modal.html',
			controller: 'popisneKomisijeModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var komisija = data.selectedKomisija;
			
			if( data.action==='odabir')
				$scope.selectedDoc.popisnaKomisija = komisija;
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
		
		$modalInstance.close({'selectedDoc':$scope.selectedDoc,
								'action':'save'});
	};

	$scope.odustani = function () {
		$modalInstance.dismiss('cancel');
	};
	
	
$scope.setSelected = function (selectedStavka) {
   $scope.selectedStavka = selectedStavka;
};
	
	$scope.dodajStavkuPD = function() {
		
		$scope.selectedStavka = new StavkaPopisnogDokumenta();
		var modalInstance = $modal.open({
			templateUrl: 'views/dodaj-stavku-popisa.html',
			controller: 'stavkaPopisaNewCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			var stavka = data.selectedStavka;
			
			if( data.action==='save')
				$scope.selectedDoc.stavke.push(stavka);
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

		$scope.izmeniStavkuPD = function() {
		
		if ($scope.selectedStavka) {
			var modalInstance = $modal.open({
				templateUrl: 'views/dodaj-stavku-popisa.html',
				controller: 'stavkaPopisaNewCtrl',
				scope: $scope
			});
			modalInstance.result.then(function (data) {}
				,
				function (response) {
					if (response.status === 500) {
						$scope.greska = "greska";
					}
				$route.reload();
				//ako stavka treba da se obrise izbaci se iz niza
				
			}, function () {
				$log.info('Modal dismissed at: ' + new Date());
			});
			}
		}

		$scope.obrisiStavkuPD = function() {
			if ($scope.selectedStavka) {
				var index = $scope.selectedDoc.stavke.indexOf($scope.selectedStavka);
				$scope.selectedDoc.stavke.splice(index,1);
			}
		}
		
$scope.setSelected = function (selectedStavka) {
   $scope.selectedStavka = selectedStavka;
};
	
	$scope.selektujMagacin = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/magacin-modal.html',
			controller: 'magaciniModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedMagacin = data.selectedMagacin;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.magacin = selectedMagacin;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
	
		$scope.selektujPredsednikaKomisije = function (size) {	
		var modalInstance = $modal.open({
			templateUrl: 'views/zaposleni-modal.html',
			controller: 'zaposleniModalCtrl',
			size:'lg',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedPredsednik = data.selectedZaposleni;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.zaposleni1 = selectedPredsednik;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
		}
		
		$scope.selektujClana1 = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/zaposleni-modal.html',
			controller: 'zaposleniModalCtrl',
			size:'lg',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedClan1 = data.selectedZaposleni;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.zaposleni2 = selectedClan1;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
		}

		$scope.selektujClana2 = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/zaposleni-modal.html',
			controller: 'zaposleniModalCtrl',
			size:'lg',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedClan2 = data.selectedZaposleni;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.zaposleni3 = selectedClan2;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
		}	

});

