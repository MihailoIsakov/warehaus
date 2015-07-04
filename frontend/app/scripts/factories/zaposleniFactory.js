angular.module('resource.zaposleni', ['ngResource'])
	.factory('Zaposleni', function ($resource) {
	return $resource('http://localhost:8080/xws/api/zaposleni/:invoiceItemId',null, {
         'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

