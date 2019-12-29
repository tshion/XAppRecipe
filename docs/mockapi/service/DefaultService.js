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
    "id" : "id",
    "isFinish" : true,
    "title" : "title"
  }, {
    "id" : "id",
    "isFinish" : true,
    "title" : "title"
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

