(() => {
  'use stricts'

  angular.module('webProject').factory('GroupFactory', [
    '$http',
    'consts',
    GroupFactory
  ])

  function GroupFactory($http, consts) {
    const vm = this

    vm.list = () => {
      $http.get(`${conts.apiUrl}/${consts.apiUrlGroup}`)
    }
    
  }
})