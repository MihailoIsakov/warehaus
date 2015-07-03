angular.module('resource.poslGodina', ['ngResource'])
	.factory('PoslovnaGodina', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina/:invoiceItemId',null, {
        'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

