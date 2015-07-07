'use strict';

 angular.module('article', ['resource.invoice',
    'resource.magacinskaKartica',
 	'angular-md5'])

 .controller('lagerListCtrl', function (Artikal, MagacinskaKartica, $scope, $routeParams, $modal, $log, $location, InvoiceItem) {

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
	$scope.koriguj = function () {
		if($scope.selectedDoc){
 			$location.path('/korekcija/'+$scope.selectedDoc.idMagacinskaKartica);
 		}
 		
	};
	
	$scope.nivAll = function (size) {
		$scope.nivArt = [];
		angular.forEach($scope.articles,function(value,index){
			var kol = value.pocetnoStanjeKol+value.kolUlaza-value.kolIzlaza;
			var beforeVal = kol * value.prosecnaCena;
			var val = value.pocetnoStanjeVr+value.vrUlaza-value.vrIzlaza;
			if(beforeVal !== val){
				$scope.nivArt.push(value);
			}
		})
		$scope.modalID = $routeParams.invoiceId;
		$scope.nivCnt = ($scope.nivArt.length > 0) ? true : false;
		$scope.query= "";
		$scope.orderProp= "idMagacinskaKartica";
		$scope.reverseSort = false;
		var modalInstance = $modal.open({
			templateUrl: 'views/nivAll.html',
			controller: 'nivAllCtrl',
			size: size,
			scope: $scope
		});
	};
	
	$scope.nivelisi = function () {
		if($scope.selectedDoc){
 			$location.path('/nivelacija/'+$scope.selectedDoc.idMagacinskaKartica);
 		}
 		
	};

$scope.btnPrint = function () {
		
    	printElement(document.getElementById("printThis"));
    	var modThis = document.querySelector("#printSection .modifyMe");
 		window.print();
}

function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    
    $printSection.appendChild(domClone);
}

$scope.setSelected = function (selectedDoc) {
   $scope.selectedDoc = selectedDoc;
};

$scope.showCard = function(article, size) {
    MagacinskaKartica.findById({'idMagacinskaKartica' : article.idMagacinskaKartica}).$promise.then(function(data) {
        $scope.kartice = [data];
    });
    $scope.query= "";
    $scope.orderProp= "idMagacinskaKartica";
    $scope.reverseSort = false;
	var modalInstance = $modal.open({
		templateUrl: 'views/kartica.html',
		controller: 'karticaCtrl',
		size: size,
        scope: $scope
	});
}

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

