var homeAppmodule = angular.module('sqlApp', []);

homeAppmodule.controller('sqlController', function($scope, $http, $rootScope, $stateParams, $state) {


    $scope.data = [{'sqlKey':'a','name':'http://www.taobao.com/'},{'sqlKey':'京东','name':'http://www.jd.com/'},{'sqlKey':'唯品会','name':'http://www.vip.com/'},{'sqlKey':'百度','name':'http://www.baidu.com/'}];
	
});