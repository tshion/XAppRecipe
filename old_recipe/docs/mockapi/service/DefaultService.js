'use strict';


/**
 * 登録したToDo 一覧の取得
 *
 * returns inline_response_200
 **/
exports.v1TodoGET = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "items" : [ {
    "id" : "1",
    "is_finish" : false,
    "title" : "やることその１",
    "update_date" : "2000-01-23T04:56:07.000+00:00"
  }, {
    "id" : "2",
    "is_finish" : true,
    "title" : "やることその２",
    "update_date" : "2000-01-23T04:56:07.000+00:00"
  }, {
    "id" : "4",
    "title" : "やることその４",
    "update_date" : "2000-01-23T04:56:07.000+00:00"
  }, {
    "id" : "5",
    "is_finish" : true,
    "update_date" : "2000-01-23T04:56:07.000+00:00"
  } ]
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * ToDo 削除
 *
 * id String 識別番号
 * no response value expected for this operation
 **/
exports.v1TodoIdDELETE = function(id) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * ToDo 編集
 *
 * body ToDo 
 * id String 識別番号
 * no response value expected for this operation
 **/
exports.v1TodoIdPUT = function(body,id) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}


/**
 * ToDo 新規登録
 *
 * body ToDo 
 * no response value expected for this operation
 **/
exports.v1TodoPOST = function(body) {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

