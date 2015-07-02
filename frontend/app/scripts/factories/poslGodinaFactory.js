angular.module('resource.poslGodina', ['ngResource'])
	.factory('PoslovnaGodina', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina',null, {
        'create': { method:'POST' }
    });
})

