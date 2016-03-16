'use strict';

angular.module('jhipsterApp')
    .controller('SkillDetailController', function ($scope, $rootScope, $stateParams, entity, Skill, Employee) {
        $scope.skill = entity;
        $scope.load = function (id) {
            Skill.get({id: id}, function(result) {
                $scope.skill = result;
            });
        };
        var unsubscribe = $rootScope.$on('jhipsterApp:skillUpdate', function(event, result) {
            $scope.skill = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
