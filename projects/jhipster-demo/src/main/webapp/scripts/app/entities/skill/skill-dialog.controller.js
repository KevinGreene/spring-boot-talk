'use strict';

angular.module('jhipsterApp').controller('SkillDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Skill', 'Employee',
        function($scope, $stateParams, $uibModalInstance, entity, Skill, Employee) {

        $scope.skill = entity;
        $scope.employees = Employee.query();
        $scope.load = function(id) {
            Skill.get({id : id}, function(result) {
                $scope.skill = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jhipsterApp:skillUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.skill.id != null) {
                Skill.update($scope.skill, onSaveSuccess, onSaveError);
            } else {
                Skill.save($scope.skill, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
