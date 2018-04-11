(() => {
  'use stricts'

  angular.module('webProject').service('GroupsService', [
    '$http',
    'consts',
    GroupsService
  ])

  function GroupsService($http, consts) {
    const vm = this

    vm.getAll = () => {
      return $http.get(`${consts.apiUrl}${consts.apiUrlGroups}`)
    }

    vm.getById = (id) => {
      return $http.get(`${consts.apiUrl}${consts.apiUrlGroups}/${id}`)
    }

    vm.save = (group) => {
      return $http.post(`${consts.apiUrl}${consts.apiUrlGroups}`, group)
    }

    vm.udpate = (group) => {
      return $http.put(`${consts.apiUrl}${consts.apiUrlGroups}`, group)
    }

    vm.delete = (group) => {
      return $http.delete(`${consts.apiUrl}${consts.apiUrlGroups}/${id}`)
    }

  }
})()