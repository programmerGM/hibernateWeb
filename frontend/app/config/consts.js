(() => {
  angular.module('webProject').constant('consts', {
    appName: 'Web Project',
    version: '1.0',
    owner: 'Maurício Generoso',
    apiUrl: 'http://localhost:3000',
    apiUrlGroups: '/groups'
  }).run([
    '$rootScope',
    'consts',
    function ($rootScope, consts) {
      $rootScope.consts = consts
    }
  ])
})()