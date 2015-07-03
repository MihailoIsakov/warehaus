angular.module('resource.vrstaPD', ['ngResource'])
	.factory('VrstaPD', function ($resource) {
	return $resource('http://localhost:8080/xws/api/vrstaPD',null, {
        'create': { method:'POST' }
    });
})

