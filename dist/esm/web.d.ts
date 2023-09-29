import { WebPlugin } from '@capacitor/core';
import type { NativeRequestPlugin } from './definitions';
export declare class NativeRequestWeb extends WebPlugin implements NativeRequestPlugin {
    formPost(options: {
        url: string;
        data: any;
        headers: any;
    }): Promise<string>;
    get(options: {
        url: string;
        headers: any;
    }): Promise<string>;
    post(options: {
        url: string;
        headers: any;
        data: any;
    }): Promise<string>;
}
