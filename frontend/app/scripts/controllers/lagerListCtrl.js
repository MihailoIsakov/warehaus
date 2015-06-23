'use strict';

 angular.module('article', ['resource.invoice',
 	'angular-md5'])

 .controller('lagerListCtrl', function (Artikal, $scope, $routeParams, $modal, $log, $location, InvoiceItem) {

if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		if(invoiceId){
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Artikal.query({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.articles = data;
		});
	}



	//ako kreiramo novu fakutru
	else{
		$scope.articles = new Artikal();
		$scope.articles.invoiceItems = [];
	}
}
	//funkcija koja otvara datepicker
	
	//modalni dijalog za stavku fakutre
	$scope.koriguj = function () {
		if($scope.selectedDoc){
 			$location.path('/korekcija/'+$scope.selectedDoc.idMagacinskaKartica);
 		}
 		else{
			$location.path('/invoice/new');
 		}
	};

	

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};

$scope.articles = "";
	$scope.options = Artikal.query();
	$log.info($scope.articles.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	$scope.insertOrEditInvoice = function (article) {
 		if(article){
 			$location.path('/lager-list/'+article);
 		}
 		else{
			$location.path('/invoice/new');
 		}
 	}
});

