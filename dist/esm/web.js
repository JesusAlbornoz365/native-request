import { WebPlugin } from '@capacitor/core';
export class NativeRequestWeb extends WebPlugin {
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
//# sourceMappingURL=web.js.map