'use strict';

angular.module('jhipsterApp')
	.controller('SkillDeleteController', function($scope, $uibModalInstance, entity, Skill) {

        $scope.skill = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Skill.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
