var exec = require('cordova/exec');

exports.print = function(ipaddress,bclabels, success, error) {
    exec(success, error, "ZplOverTcpIp", "print", [ipaddress,bclabels]);
};
