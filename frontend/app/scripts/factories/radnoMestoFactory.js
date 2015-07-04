angular.module('resource.radnoMesto', ['ngResource'])
	.factory('RadnoMesto', function ($resource) {
	return $resource('http://localhost:8080/xws/api/radno-mesto/:invoiceItemId',null, {
         'create': { method:'POST' },
          'update': { method:'PUT' }
    });
})

