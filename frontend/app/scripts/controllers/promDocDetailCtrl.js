'use strict';

 angular.module('promDocDetail',['ui.bootstrap'])

 .controller('promDocDetailCtrl', function ($scope, $modal, $modalInstance, StavkaPD, VrstaPD, Magacin, PoslovnaGodina, datepickerPopupConfig) {
	
	$scope.today = new Date();
	datepickerPopupConfig.currentText = "Danas";
	datepickerPopupConfig.clearText="Obriši";
	$scope.show = false;
	$scope.vrstaIndex = 0;
	$scope.godinaIndex = 0;
	if (!$scope.selectedDoc.datumNastanka) {
		$scope.selectedDoc.datumNastanka = new Date();
	}
	
	//poslovneGodine za drop down
	VrstaPD.query().$promise.then(function (data) {
			$scope.vrste = data;
			var i=0;
			for (i=0; i<$scope.vrste.length; i++) {
				if ($scope.vrste[i].idVrstaDokumenta==$scope.selectedDoc.vrstaDokumenta.idVrstaDokumenta) {
					$scope.vrstaIndex = i;
					$scope.vrste[i] = $scope.selectedDoc.vrstaDokumenta;
				}
			}
	});
	
	//poslovneGodine za drop down
	PoslovnaGodina.query().$promise.then(function (data) {
			$scope.poslovneGodine = data;
			$scope.godinaIndex = $scope.poslovneGodine.indexOf($scope.selectedDoc.poslovnaGodina);
			var i=0;
			for (i=0; i<$scope.poslovneGodine.length; i++) {
				if ($scope.poslovneGodine[i].idPoslovnaGodina==$scope.selectedDoc.poslovnaGodina.idPoslovnaGodina) {
					$scope.poslovneGodine[i] = $scope.selectedDoc.poslovnaGodina;
				}
			}
	});
	
	$scope.zatvaranje = function() {
		$modalInstance.dismiss('cancel');
	}
	
	//funkcija koja otvara datepicker
	$scope.openDatumNastankaDatepicker = function($event, datumNastankaOpened) {
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
				$scope.selectedDoc.poslovniPartner = partner;
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
		if (!$scope.selectedDoc.datumNastanka) {
			$scope.selectedDoc.datumNastanka = $scope.today;
		}
		if (!$scope.selectedDoc.vrstaDokumenta.nazivVrste == "primka") {
			$scope.selectedDoc.magacin2 = null;
		}
		if (!$scope.selectedDoc.vrstaDokumenta.nazivVrste == "otpremnica") {
			$scope.selectedDoc.magacin1 = null;
		}
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
		
		$scope.selectedStavka = new StavkaPD();
		var modalInstance = $modal.open({
			templateUrl: 'views/stavkaPDDetail.html',
			controller: 'stavkaModalCtrl',
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
				templateUrl: 'views/stavkaPDDetail.html',
				controller: 'stavkaModalCtrl',
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

	
	$scope.selektujMagacin1 = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/magacin-modal.html',
			controller: 'magaciniModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedMagacin1 = data.selectedMagacin;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.magacin1 = selectedMagacin1;
			},
            function (response) {
                if (response.status === 500) {
                    $scope.greska = "greska";
                }
			$route.reload();
			
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});}
	
	
	$scope.selektujMagacin2 = function () {	
		var modalInstance = $modal.open({
			templateUrl: 'views/magacin-modal.html',
			controller: 'magaciniModalCtrl',
			scope: $scope
		});
		modalInstance.result.then(function (data) {
			
			var selectedMagacin2 = data.selectedMagacin;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi

			if( data.action==='save')
				$scope.selectedDoc.magacin2 = selectedMagacin2;
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
		}
);

