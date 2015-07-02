'use strict';

/**
 * @ngdoc overview
 * @name invoiceClientApp
 * @description
 * # invoiceClientApp
 *
 * Main module of the application.
 */
 angular
 .module('invoiceClientApp', [
  'ngAnimate',
  'ngCookies',
  'ngResource',
  'ngRoute',
  'ngSanitize',
  'ngTouch',
  'main',
  'about',
  'article',
  'promDoc',
  'invoice',
  'correction',
  'nivelation',
  'primka',
  'stonPrim',
  'user',
  'partner',
  'resource.user',
  'resource.promDoc',
  'resource.artikale',
  'resource.magacinska',
  'resource.magacinskaN',
  'resource.primka',
  'resource.partner',
  'resource.drzave',
  'drzave',
  'resource.mesta',
  'mesta',
  'resource.poslGodina',
  'poslGodina',
  'resource.preduzeca',
  'preduzeca',
  'resource.zaposleni',
  'resource.magacinska',
  'resource.magacinskaKartica',
  'resource.magacin',
  'resource.jedinicaMere',
  'resource.radnoMesto',
  'resource.grupaArtikala',
  'resource.praviArtikal'
  ])
 .config(function ($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl: 'views/lager-list.html',
    controller: 'lagerListCtrl'
  })
  .when('/lager-list', {
    templateUrl: 'views/lager-list.html',
    controller: 'lagerListCtrl'
  })
  .when('/lager-list/:invoiceId', {
    templateUrl: 'views/lager-list.html',
    controller: 'lagerListCtrl'
  })
   .when('/prometni-dokumenti', {
    templateUrl: 'views/prometni-dokumenti.html',
    controller: 'promDocCtrl'
  })
   .when('/korekcija/:karticaId', {
    templateUrl: 'views/korekcija.html',
    controller: 'correctionCtrl'
  })
   .when('/nivelacija/:karticaId', {
    templateUrl: 'views/nivelacija.html',
    controller: 'nivCtrl'
  })
   .when('/primka/:primkaId', {
    templateUrl: 'views/primka.html',
    controller: 'primkaCtrl'
  })
   .when('/primka', {
    templateUrl: 'views/primka.html',
    controller: 'primkaCtrl'
  })
   .when('/poslovni-partner', {
    templateUrl: 'views/poslovni-partner.html',
    controller: 'partnerCtrl'
  })
   .when('/poslovni-partner/:partnerId', {
    templateUrl: 'views/poslovni-partner.html',
    controller: 'partnerCtrl'
  })
  .when('/login', {
    templateUrl: 'views/login.html',
    controller: 'userCtrl'
  })
  .otherwise({
    redirectTo: '/'
  });
})
 //tricky deo
 .factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){// fabrika koja pravi interceptor
  return {
    response: function(response){//ako smo dobili noramalan odgovor vratimo taj odgovor
      if (response.status === 401) {
        console.log("Response 401");
      }
      return response || $q.when(response);
    },
    responseError: function(rejection, x, y) {//ako smo dobili gresku
      if (rejection.status === 401) {//ako je greska 401 (korisnik nije prijavljen na sistem)
        console.log("Response Error 401",rejection);
        $location.path('/login');//redirektujemo se na login
      }
      return $q.reject(rejection);//i odbacimo zahtev
    }
  }
}])
 .config(['$httpProvider',function($httpProvider) {//interceptor dodamo u stek interceptora
  $httpProvider.interceptors.push('authHttpResponseInterceptor');
}])
 //imamo problem: koristimo $resource koji je wrapper oko $http
 //kada pristigne odgovor prvo se uradi transformResponse nad odgovorom
 //pa se onda presretne odgovor.
 //uz gresku sa servera pristigne tekstualni opis greske
 //"Not logged in"
 //ovaj opis greske ne moze da se konvertuje u JSON (pogeldaj slajd $resource - napisano sitnim slovima)
 //zbog toga moramo da izmenimo transformResponce 
 .run(['$http','$location',//to radimo u run, jer se izvrsava pre svega ostalog
  function($http, $location) {
    var parseResponse = function(response, headers, status) {//ova funkcija proba da konvertuje pristigli odgovor u JSON
      if(status===401){//ako je odgovor neautorizovan radimo redirekciju
        $location.path('login');
      }
      else{
        return response;//inace vratimo odgovor
      }
    };

    $http.defaults.transformResponse.unshift(parseResponse);//ovu funkciju stavimo na pocetak niza transformer funkcija
  }
  ])
 .controller('appCtrl', function($scope, User, $log, $location, $modal){
  $scope.logout = User.logout;
  $scope.isLoginPage = function () {
    return $location.path() === '/login';
  };
  $scope.about = function (size) {
    var modalInstance = $modal.open({
      templateUrl: 'views/about.html',
      controller: 'AboutCtrl',
      size: size,
    });
  };
});