(() => {
  'use stricts'

  angular.module('webProject').controller('HomeController', [
    '$scope',
    '$http',
    'consts',
    HomeController
  ])

  function HomeController($scope, $http, consts) {
    const vm = this

    vm.groups = [{}]

    vm.refresh = () => {
      $http.get(`${consts.apiUrl}${consts.apiUrlGroups}`).then((response) => {
        groups = response.data
      }).catch((response) => {
        console.log('erro na requisição dos grupos.')
      })
      vm.groups = [
        {
          nameGroup: 'Teste',
          students: [{}]
        }
      ]
    }

    vm.refresh()
  }

})()