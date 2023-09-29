import { registerPlugin } from '@capacitor/core';

import type { NativeRequestPlugin } from './definitions';

const NativeRequest = registerPlugin<NativeRequestPlugin>('NativeRequest', {
  web: () => import('./web').then(m => new m.NativeRequestWeb()),
});

export * from './definitions';
export { NativeRequest };
