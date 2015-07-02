angular.module('resource.preduzeca', ['ngResource'])
	.factory('Preduzeca', function ($resource) {
	return $resource('http://localhost:8080/xws/api/preduzeca',null, {
        'create': { method:'POST' }
    });
})

