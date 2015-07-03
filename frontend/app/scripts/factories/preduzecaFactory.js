angular.module('resource.preduzeca', ['ngResource'])
	.factory('Preduzeca', function ($resource) {
	return $resource('http://localhost:8080/xws/api/preduzeca/:invoiceItemId',null, {
         'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

