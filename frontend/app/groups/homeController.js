(() => {
  'use stricts'

  angular.module('webProject').controller('HomeController', [
    '$scope',
    '$http',
    'consts',
    'msgs',
    HomeController
  ])

  function HomeController($scope, $http, consts, msgs) {
    const vm = this

    vm.groups = [{}]

    vm.refresh = () => {
      $http.get(`${consts.apiUrl}${consts.apiUrlGroups}`).then((response) => {
        vm.groups = response.data
      }).catch((response) => {
        msgs.addError('Erro ao obter dados da lista no servidor.')
      })
      vm.groups = [
        {
          nameGroup: 'Teste1',
          students: [{}]
        },
        {
          nameGroup: 'Teste2',
          students: [{}]
        },
        {
          nameGroup: 'Teste3',
          students: [{}]
        }
      ]
    }

    vm.refresh()
  }
})()