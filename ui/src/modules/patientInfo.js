﻿'use strict';

import angular from 'angular';
import '../entries/main.js';

var patientInfo = angular.module('patientInfo', ['main']);

patientInfo.controller('patientInfoController', ['$scope', '$http', '$state', function($scope, $http, $state) {

  if(sessionStorage.getItem('patientId')) {
    $scope.myModalContent = '确认修改基本资料吗？';
  }else{
    $scope.myModalContent = '确认提交吗？';
  }
  var patient = {};
  $scope.patientMenuClick();
  $scope.familyHistory = '无';


  $scope.showOthers = function(){
    $scope.showOthersResult = (!$scope.smoke || !$scope.drink || ($scope.familyHistory == '无')) ? false : true;
  };

  $scope.showDatepicker = function() {
    $scope.concurrentDatepicker = !$scope.concurrentAutoDisease == true ? false : true;
  };

  $('#datepicker1').datepicker({
    autoclose: true
  });

  $('#datepicker2').datepicker({
    autoclose: true
  });

  $('#datepicker3').datepicker({
    autoclose: true
  });

  $('#datepicker4').datepicker({
    autoclose: true
  });

  $('#datepicker5').datepicker({
    autoclose: true
  });

  $('#datepicker6').datepicker({
    autoclose: true
  });


  //姓名失焦事件
  $scope.judgeName = function() {
    if($scope.name == undefined || $scope.name == '') {
      $('#inputName').removeAttr('data-content');
      $('#inputName').attr('data-content', '不能为空');
      $('#inputName').popover('show');
    }else if($scope.name.length < 2 || $scope.name.length >10) {
      $('#inputName').removeAttr('data-content');
      $('#inputName').attr('data-content', '长度不符合要求');
      $('#inputName').popover('show');
    }else{
      $('#inputName').removeAttr('data-content');
      $('#inputName').attr('data-content', '');
    }
  };

  $scope.showNextModel = function() {
    $('#myModal').modal('show');
  };

  $scope.showExistModal = function() {
    $('#existModal').modal('show');
  };

  $scope.exsitEdit = function() {
    $('#existModal').modal('toggle');
    // window.location.href = index.html;
    setTimeout(function() {
      $state.go('home');
    }, 1000);
  };

  //毫秒转换年月日
  function toPre(date) {
    var unixTimestamp = new Date(date);
    return (unixTimestamp.getMonth() + 1) + '/' + unixTimestamp.getDate() + '/' + unixTimestamp.getFullYear();
  }

  // 06/14/2017 ==> 2017-06-14
  function formatDateFromBack(date) {
    var dateArr = date.split('/');
    var year = dateArr[2];
    var month = dateArr[0];
    var day = dateArr[1];
    return year + '-' + month + '-' + day;
  }

  //非创建用户按钮    取个人信息
  if(sessionStorage.getItem('patientId')) {
    $http({
      method:'GET',
      url:'api/patient/' + sessionStorage.getItem('patientId')
    }).then(function(response){
      patient = response.data;
      $scope.name = patient.name;
      $scope.gender = patient.gender;
      $scope.height = patient.height;
      $scope.nation = patient.nation;
      $scope.birthday = toPre(patient.birthday);
      $scope.weight = patient.weight;
      $scope.bmi = patient.bmi;
      $scope.degreeOfEducation = patient.degreeOfEducation;
      $scope.firstTimeLiverInjury = toPre(patient.firstTimeLiverInjury);
      $scope.investigateHospital = patient.investigateHospital;
      $scope.telephone = patient.telephone;
      $scope.durationOfVisit = patient.durationOfVisit;
      $scope.firstVisitAge = patient.firstVisitAge;
      $scope.firstVisitTime = toPre(patient.firstVisitTime);
      $scope.smoke = patient.smoke;
      $scope.drink = patient.drink;
      $scope.familyHistory = patient.familyHistory;
      $scope.smokeDrinkFamHis = patient.smokeDrinkFamHis;
      $scope.concurrentAutoDisease = patient.concurrentAutoDisease;
      $scope.concurrentAutoDate = toPre(patient.concurrentAutoDate);
      $scope.conAutoDisFirstOrNot = patient.conAutoDisFirstOrNot;
    });
  }
  //保存 --> 确定  按钮
  $scope.commit = function() {
    patient.name = $scope.name;
    patient.gender = $scope.gender;
    patient.height = $scope.height;
    patient.nation = $scope.nation;
    patient.birthday = formatDateFromBack($scope.birthday);
    patient.weight = $scope.weight;
    patient.bmi = $scope.bmi;
    patient.degreeOfEducation = $scope.degreeOfEducation;
    patient.firstTimeLiverInjury = formatDateFromBack($scope.firstTimeLiverInjury);
    patient.investigateHospital = $scope.investigateHospital;
    patient.telephone = $scope.telephone;
    patient.durationOfVisit = $scope.durationOfVisit;
    patient.firstVisitAge = $scope.firstVisitAge;
    patient.firstVisitTime = formatDateFromBack($scope.firstVisitTime);
    patient.smoke = $scope.smoke;
    patient.drink = $scope.drink;
    patient.familyHistory = $scope.familyHistory;
    patient.smokeDrinkFamHis = $scope.smokeDrinkFamHis;
    patient.concurrentAutoDisease = $scope.concurrentAutoDisease;
    patient.concurrentAutoDate = formatDateFromBack($scope.concurrentAutoDate);
    patient.conAutoDisFirstOrNot = $scope.conAutoDisFirstOrNot;
    patient.complete = true;
    if(!sessionStorage.getItem('patientId')) {
      $http({
        method:'POST',
        url:'/api/patient',
        data: patient
      }).then(function(response) {
        sessionStorage.setItem('patientId', response.data);
        $('#mymodal').modal('hide');
        $scope.justModalContent = '操作成功！';
        setTimeout(function(){
          $('#justModal').modal('show');
        }, 500);
      });
    }else{
      patient.id = sessionStorage.getItem('patientId');
      $http({
        method:'PUT',
        url:'api/patient',
        data: patient
      }).then(function(){
        $('#mymodal').modal('hide');
        $scope.justModalContent = '基本资料更新成功！';
        setTimeout(function(){
          $('#justModal').modal('show');
        }, 500);
      });
    }
  };



}]);
