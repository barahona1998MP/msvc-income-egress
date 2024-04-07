const axios = require("axios");
const registry = require("../config/registry.json");
const { verifyToken } = require("../src/middleware/auth")

exports.proxyRequest = (req, res) => {
    console.log(req.params.apiName);
    if (registry.services[req.params.apiName]) {
        axios({
            method: req.method,
            url: registry.services[req.params.apiName].url + req.params.path,
            headers: req.headers,
            data: req.body
        }).then((response) => {
            console.log("Method: " + req.method);
            res.send(response.data);
        });
    } else {
        res.send("API Name doesn't exist");
    }
};


exports.proxyRequestAuthenticated = [verifyToken, this.proxyRequest];
