angular.module('resource.zaposleni', ['ngResource'])
	.factory('Zaposleni', function ($resource) {
	return $resource('http://localhost:8080/xws/api/zaposleni/:idZaposleni', { idZaposleni: '@idZaposleni' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})