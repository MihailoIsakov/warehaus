angular.module('resource.artikale', ['ngResource'])
	.factory('Artikale', function ($resource) {
	return $resource('http://localhost:8080/xws/api/magacinska-kartica/:id',null, {
        'findByMagCardId': { method:'GET' }
    });
})