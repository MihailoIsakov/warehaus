angular.module('resource.grupaArtikala', ['ngResource'])
	.factory('GrupaArtikala', function ($resource) {
	return $resource('http://localhost:8080/xws/api/grupa-artikala/:idGrupaArtikala', { idGrupaArtikala: '@idGrupaArtikala' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})