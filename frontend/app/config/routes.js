(() => {
  angular.module('webProject').config([
    '$stateProvider',
    '$urlRouterProvider',
    '$httpProvider',
    ($stateProvider, $urlRouterProvider, $httpProvider) => {
      $stateProvider.state('home', {
        url: '/',
        templateUrl: '../home/home.html'
      }).state('groups', {
        url: '/groups',
        templateUrl: '../groups/form.html'
      })

      $urlRouterProvider.otherwise('/')
    }
  ])
})()