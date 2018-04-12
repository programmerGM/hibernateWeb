(() => {
  'use stricts'

  angular.module('webProject').service('GroupService', [
    'GroupFactory',
    GroupService
  ])

  function GroupService(GroupFactory) {
    const vm = this

    vm.getGroup = (id) => {
      //return GroupFactory.getGroup({ id: id }).$promise;
    }

    vm.list = () => {
      //return GroupFactory.list().$promise;
    }

    vm.save = (group) => {
      //if (group.id) {
      // return GroupFactory.atualizar({ id: group.id }, group).$promise;
      //} else {
      //  return GroupFactory.save(group).$promise;
      // }
    }

    vm.excluir = (group) => {
      //  return GroupFactory.delete({ id: group.id }).$promise;
    }
  }
})()