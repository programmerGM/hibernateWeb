(() => {
  'use stricts'

  angular.module('webProject').controller('HomeController', [
    '$scope',
    '$http',
    '$window',
    'consts',
    'msgs',
    'GroupsService',
    HomeController
  ])

  function HomeController($scope, $http, $window, consts, msgs, GroupsService) {
    const vm = this
    const groupsService = GroupsService

    vm.groups = [
      {
        id: 1,
        nameGroup: 'Teste1',
        students: [{
          id: 1,
          nameStudent: 'TesteStudent1'
        }]
      },
      {
        id: 2,
        nameGroup: 'Teste2',
        students: [{
          id: 2,
          nameStudent: 'TesteStudent2'
        }]
      },
      {
        id: 3,
        nameGroup: 'Teste3',
        students: [{
          id: 3,
          nameStudent: 'TesteStudent3'
        }]
      }, 
      {
        id: 4,
        nameGroup: 'Teste4',
        students: [{
          id: 4,
          nameStudent: 'TesteStudent4'
        }]
      }
    ]

    vm.refresh = () => {
      $window.location.href = '/#!/'
    }

    vm.getAll = () => {
      // groupsService.getAll().then((response) => {
      //   vm.groups = response.data
      // }).catch((response) => {
      //   // msgs.addError('Erro ao obter dados da lista no servidor.')
      // })
      return vm.groups
    }

    vm.getById = (id) => {

    }

    vm.save = (group) => {

    }

    vm.update = (group) => {

    }

    vm.delete = (index, group) => {
      console.log(group)
      vm.groups.splice(index, 1)
    }

    vm.redirectNewGroup = () => {
      console.log('passou')
      $window.location.href = '/#!/groups'
    }
  }
})()