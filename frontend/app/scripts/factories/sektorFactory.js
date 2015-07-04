angular.module('resource.sektor', ['ngResource'])
	.factory('Sektor', function ($resource) {
	return $resource('http://localhost:8080/xws/api/sektor/:invoiceItemId',null, {
        'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

