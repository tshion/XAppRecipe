'use strict';

var utils = require('../utils/writer.js');
var Default = require('../service/DefaultService');

module.exports.v1TodoGET = function v1TodoGET (req, res, next) {
  Default.v1TodoGET()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.v1TodoIdDELETE = function v1TodoIdDELETE (req, res, next) {
  var id = req.swagger.params['id'].value;
  Default.v1TodoIdDELETE(id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.v1TodoIdPUT = function v1TodoIdPUT (req, res, next) {
  var body = req.swagger.params['body'].value;
  var id = req.swagger.params['id'].value;
  Default.v1TodoIdPUT(body,id)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.v1TodoPOST = function v1TodoPOST (req, res, next) {
  var body = req.swagger.params['body'].value;
  Default.v1TodoPOST(body)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
