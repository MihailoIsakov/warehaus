angular.module('resource.stavkaPopisnogDokumenta', ['ngResource'])
	.factory('StavkaPopisnogDokumenta', function ($resource) {
	return $resource('http://localhost:8080/xws/api/stavkaPopisnogDokumenta/:stavkaId',null, {
        'update': { method:'PUT'},
		'create': {method:'POST'}
    });
})