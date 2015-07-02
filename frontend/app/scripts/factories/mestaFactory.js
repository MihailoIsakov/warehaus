angular.module('resource.mesta', ['ngResource'])
	.factory('Mesta', function ($resource) {
	return $resource('http://localhost:8080/xws/api/mesta/:invoiceItemId',null, {
        'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

