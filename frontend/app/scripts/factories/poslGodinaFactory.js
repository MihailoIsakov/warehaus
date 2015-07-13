angular.module('resource.poslGodina', ['ngResource'])
	.factory('PoslovnaGodina', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina/:invoiceItemId', {invoiceItemId:'@invoiceItemId' }, {
        'create': { method:'POST' },
        'findById': { method:'GET'}
      
    });
})

