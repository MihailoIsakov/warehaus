angular.module('resource.pGodina', ['ngResource'])
	.factory('pGodina', function ($resource) {
	return $resource('http://localhost:8080/xws/api/poslovna-godina-open/:idPoslovnaGodina', {idPoslovnaGodina:'@idPoslovnaGodina' }, {
		'update': { method:'GET' }
    });
})