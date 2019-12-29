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
  Default.v1TodoIdDELETE()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.v1TodoIdPUT = function v1TodoIdPUT (req, res, next) {
  Default.v1TodoIdPUT()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.v1TodoPOST = function v1TodoPOST (req, res, next) {
  Default.v1TodoPOST()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
