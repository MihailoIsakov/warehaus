angular.module('resource.analitika', ['ngResource'])
	.factory('Analitika', function ($resource) {
	return $resource('http://localhost:8080/xws/api/analitika/:id',null, {
         'findByMagCardId': { method:'GET', isArray:true }
    });
})