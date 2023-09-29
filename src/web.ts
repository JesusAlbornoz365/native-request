import { WebPlugin } from '@capacitor/core';

import type { NativeRequestPlugin } from './definitions';

export class NativeRequestWeb extends WebPlugin implements NativeRequestPlugin {
  async formPost(options: { url: string; data: any; headers: any; }): Promise<string> {
      console.log("FORM POST", options);
      return "Not implemented on web";
  }

  async get(options: { url: string; headers: any; }): Promise<string> {
      console.log("GET", options);
      return "Not implemented on web";
  }

  async post(options: { url: string; headers: any; data: any; }): Promise<string> {
    console.log("POST", options);
    return "Not implemented on web";
  }
}
