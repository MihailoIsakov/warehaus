angular.module('resource.popisniDokument', ['ngResource'])
	.factory('PopisniDokument', function ($resource) {
	return $resource('http://localhost:8080/xws/api/popisni-dokumenti/:id',null, {
        'create': { method:'POST'},
		'update': { method:'PUT'}
    });
})

