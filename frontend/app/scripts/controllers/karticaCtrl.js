'use strict';

 angular.module( 'karticaA', ['resource.analitika',
 	'angular-md5'])

 .controller('karticaCtrl', function ($scope, $modalInstance, Analitika) {
	 $scope.analitics = Analitika.findByMagCardId({'id': $scope.selectedDoc.idMagacinskaKartica});
});

