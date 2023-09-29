var capacitorNativeRequest = (function (exports, core) {
    'use strict';

    const NativeRequest = core.registerPlugin('NativeRequest', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.NativeRequestWeb()),
    });

    class NativeRequestWeb extends core.WebPlugin {
        async formPost(options) {
            console.log("FORM POST", options);
            return "Not implemented on web";
        }
        async get(options) {
            console.log("GET", options);
            return "Not implemented on web";
        }
        async post(options) {
            console.log("POST", options);
            return "Not implemented on web";
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        NativeRequestWeb: NativeRequestWeb
    });

    exports.NativeRequest = NativeRequest;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
