angular.module('resource.drzave', ['ngResource'])
	.factory('Drzave', function ($resource) {
	return $resource('http://localhost:8080/xws/api/drzave/:invoiceItemId',null, {
         'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

