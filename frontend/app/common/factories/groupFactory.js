(() => {
  'use strict'

  angular.module('webProject').factory('GroupFactory', [
    '$http',
    GroupFactory
  ])

  function GroupFactory($http) {

    const API = 'http://localhost:3000'
    const API_GROUPS = API + '/groups'

    function list() {
      $http.get(API_GROUPS).then(response => {
        return response.data
      }).catch(response => {
        return null
      })
    }

    function findById(id) {
      $http.get(API_GROUPS + '/' + id).then(response => {
        return response.data
      }).catch(response => {
        return null
      })
    }

    return { list, findById }
  }
})()