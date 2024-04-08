const express = require("express");
const router = express.Router();
const { proxyRequest, proxyRequestAuthenticated} = require("../controller/gatewayController");

router.all("/:apiName/:path", proxyRequestAuthenticated);

module.exports = router;
