'use strict';

angular.module('jhipsterApp')
    .factory('SkillSearch', function ($resource) {
        return $resource('api/_search/skills/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
