# native-request

Plugin for native http request

## Install

```bash
npm install native-request
npx cap sync
```

## API

<docgen-index>

* [`formPost(...)`](#formpost)
* [`get(...)`](#get)
* [`post(...)`](#post)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### formPost(...)

```typescript
formPost(options: { url: string; data: any; headers: any; }) => Promise<string>
```

| Param         | Type                                                   |
| ------------- | ------------------------------------------------------ |
| **`options`** | <code>{ url: string; data: any; headers: any; }</code> |

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------


### get(...)

```typescript
get(options: { url: string; headers: any; }) => Promise<string>
```

| Param         | Type                                        |
| ------------- | ------------------------------------------- |
| **`options`** | <code>{ url: string; headers: any; }</code> |

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------


### post(...)

```typescript
post(options: { url: string; headers: any; data: any; }) => Promise<string>
```

| Param         | Type                                                   |
| ------------- | ------------------------------------------------------ |
| **`options`** | <code>{ url: string; headers: any; data: any; }</code> |

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------

</docgen-api>
