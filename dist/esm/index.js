import { registerPlugin } from '@capacitor/core';
const NativeRequest = registerPlugin('NativeRequest', {
    web: () => import('./web').then(m => new m.NativeRequestWeb()),
});
export * from './definitions';
export { NativeRequest };
//# sourceMappingURL=index.js.map