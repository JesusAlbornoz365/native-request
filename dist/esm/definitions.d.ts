export interface NativeRequestPlugin {
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
