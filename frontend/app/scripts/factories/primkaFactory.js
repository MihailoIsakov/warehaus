angular.module('resource.primka', ['ngResource'])
	.factory('PrometniDokument', function ($resource) {
	return $resource('http://localhost:8080/xws/api/primka/:primkaId',null, {
        'update': { method:'PUT'}
    });
})