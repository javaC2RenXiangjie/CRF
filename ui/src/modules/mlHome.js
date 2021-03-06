import angular from 'angular';
import uiBootstrap from 'angular-ui-bootstrap';
import '../commons/page.js';
import '../entries/medicineLiverMain.js';

angular.module('mlHome', [uiBootstrap, 'page', 'medicineLiverMain', 'chart.js'])
  .controller('mlHomeController', ['$scope', '$http', '$state', 'localStorageService', function($scope, $http, $state, localStorageService) {
    loginStatus();
    $scope.url = '/api/mlPatient/project/2';
    $scope.mlChangeMenuStatus();
    $scope.mlPatientClick = function(patient) {
      sessionStorage.setItem('mlPatientId', patient.id);
      location.reload(true);
      $state.go('mlPatientInfo');
    };

    $scope.query = function() {
      $scope.url = '/api/mlPatient/2/q?queryStr=' + $scope.queryStr;
    };

    $scope.all = function() {
      $scope.url = '/api/mlPatient/project/2';
    };

    $scope.addPatient = function() {
      sessionStorage.removeItem('mlPatientId');
      $('#diseaseHistoryMenuYes').hide();
      $('#diseaseHistoryMenuNo').hide();
      $('#allergyMenuYes').hide();
      $('#allergyMenuNo').hide();
      $('#drinkMenuYes').hide();
      $('#drinkMenuNo').hide();
      $('#drugHistoryMenuYes').hide();
      $('#drugHistoryMenuNo').hide();
      $('#symptomsMenuYes').hide();
      $('#symptomsMenuNo').hide();
      $('#firstAbnormalExaminationMenuYes').hide();
      $('#firstAbnormalExaminationMenuNo').hide();
      $('#hospitalAbnormalExaminationMenuYes').hide();
      $('#hospitalAbnormalExaminationMenuNo').hide();
      $('#excludeOtherMenuYes').hide();
      $('#excludeOtherMenuNo').hide();
      $('#imagingEndoscopyMenuYes').hide();
      $('#imagingEndoscopyMenuNo').hide();
      $('#routineBloodMenuYes').hide();
      $('#routineBloodMenuNo').hide();
      $('#liverHistologicalMenuYes').hide();
      $('#liverHistologicalMenuNo').hide();
      $('#liverInjuryMenuYes').hide();
      $('#liverInjuryMenuNo').hide();
      $('#mlTreatmentMenuYes').hide();
      $('#mlTreatmentMenuNo').hide();
      $('#diseaseOutcomeMenuYes').hide();
      $('#diseaseOutcomeMenuNo').hide();
      $('#biologicalSamplesMenuYes').hide();
      $('#biologicalSamplesMenuNo').hide();
      $state.go('mlPatientInfo');
    };

    $scope.createExcel = function() {
      $scope.information = '正在导出，请稍候...';
      $scope.buttonShow = false;
      $('#infoModal').modal({
        keyboard: true
      });
      $http({
        method: 'POST',
        url: '/api/excel/ml'
      }).then(function success() {
        $('#infoModal').modal('hide');
        $scope.information = '导出成功';
        $scope.buttonShow = true;
        $scope.buttonContent = '确认';
        $('#infoModal').modal({
          keyboard: true
        });
        var excelUrl = 'http://localhost:8080/excelDown/' + localStorageService.get('user').realName + 'medicineLiver.xlsx';
        window.open(excelUrl);
      }, function fail() {
        $('#infoModal').modal('hide');
        $scope.information = '导出出错，请稍候再试';
        $scope.buttonShow = true;
        $scope.buttonContent = '确认';
        $('#infoModal').modal({
          keyboard: true
        });
      });
    };

    function loginStatus() {
      if (!localStorageService.get('user')) {
        window.location.href = '/login.html';
      }
    }


    $scope.labels = ['0~10', '10~20', '20~30', '30~40', '40~50', '50~60', '60~70', '70~80'];
    $scope.series = ['男', '女'];
    $scope.data = [];
    $http({
      method: 'GET',
      url: '/api/mlPatient/ageData'
    }).then(function success(res) {
      var ageData = res.data;
      $scope.data.push(ageData[0], ageData[1]);
    });
  }]);