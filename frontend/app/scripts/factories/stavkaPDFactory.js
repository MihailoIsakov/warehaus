angular.module('resource.stavkaPD', ['ngResource'])
	.factory('StavkaPD', function ($resource) {
	return $resource('http://localhost:8080/xws/api/stavkaPD/:stavkaId',null, {
        'update': { method:'PUT'},
		'create': {method:'POST'}
    });
})