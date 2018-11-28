angular.module('phonecatFilters', ['ngCookies', 'pascalprecht.translate']);

var indexApp=angular.module('indexApp', ['ui.router','ui.bootstrap','phonecatFilters','tm.pagination','ngCookies','pascalprecht.translate',
'homeApp','placeApp','sqlApp']).config(
		function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/home');
			$stateProvider.state('home', {//首页
				url : '/home',
				templateUrl : 'home.html',
				cache:'false',
				controller: 'homeController'				
			}).state('place', {//首页
                url : '/place',
                templateUrl : 'place.html',
                cache:'false',
                controller: 'placeController'
            }).state('sql', {//首页
                url : '/sql',
                templateUrl : 'sql.html',
                cache:'false',
                controller: 'sqlController'
            });
		})

	.factory('DataInfo', function() {
	return {
		name: "1"
	};
});


indexApp.controller('indexController', function($scope, $http,$cookies, $rootScope,$state,$stateParams) {
	////////////////////////////////////////////////测试数据
	$scope.user={};
	$scope.friends=[];
	$scope.friends=[{'friendName':'淘宝','href':'http://www.taobao.com/'},{'friendName':'京东','href':'http://www.jd.com/'},{'friendName':'唯品会','href':'http://www.vip.com/'},{'friendName':'百度','href':'http://www.baidu.com/'}];
	
	function init() {

	};
	
	init();
	/** ******************************************验证登录**************************************** */
	// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckUserLogined",
		}).success(function(data, status) {
				$scope.user = data.result.user;
				//console.log($scope.user.username);
		});
	}
	
	/** ******************************************登出**************************************** */
	$scope.loginOut = function(){
		$http({
			method : "POST",
			url : "../userC/logout",
		}).success(function(data, status) {
			$scope.user = {};
			$state.go("home", {}, { reload: true });
			
		});
	};
	/** ******************************************查询访问人数，交易额度**************************************** */
	function queryAdd(){
		$http({
			method : "POST",
			url : "../userC/queryAdd",
		}).success(function(data, status) {
			console.log("查询成功");
		});
	};
});