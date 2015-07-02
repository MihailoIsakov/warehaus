angular.module('resource.radnoMesto', ['ngResource'])
	.factory('RadnoMesto', function ($resource) {
	return $resource('http://localhost:8080/xws/api/radno-mesto/:idRadnoMesto', { idRadnoMesto: '@idRadnoMesto' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})