angular.module('resource.praviArtikal', ['ngResource'])
	.factory('PraviArtikal', function ($resource) {
	return $resource('http://localhost:8080/xws/api/artikal/:idArtikal', { idArtikal: '@idArtikal' }, {
        'update': { method:'PUT' },
		'delete': { method:'DELETE' },
		'create': { method:'POST' },
		'findById': { method:'GET' }
    });
})