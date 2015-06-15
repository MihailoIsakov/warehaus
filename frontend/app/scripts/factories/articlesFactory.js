angular.module('resource.invoice', ['ngResource'])
	.factory('Artikal', function ($resource) {
	return $resource('http://localhost:8080/xws/api/invoice/:invoiceId',null, {
        'update': { method:'PUT' }
    });
})

