(() => {
  angular.module('webProject').config([
    '$stateProvider',
    '$urlRouterProvider',
    ($stateProvider, $urlRouterProvider) => {
      $stateProvider.state('home', {
        url: '/',
        templateUrl: '../home/home.html'
      }).state('groups', {
        url: '/groups',
        templateUrl: '../groups/form.html'
      })
      .state('groupsId', {
        url: '/groups/:id',
        templateUrl: '../groups/form.html'
      })

      $urlRouterProvider.otherwise('/')
    }
  ])
})()