# capacitor-facebook-ad

Capacitor plugin for facecbook ads

## Install

```bash
npm install capacitor-facebook-ad
npx cap sync
```

## API

<docgen-index>

* [`initialize(...)`](#initialize)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### initialize(...)

```typescript
initialize(options: CapacitorFacebookAdInitializationOptions) => any
```

| Param         | Type                                                                                                          |
| ------------- | ------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#capacitorfacebookadinitializationoptions">CapacitorFacebookAdInitializationOptions</a></code> |

**Returns:** <code>any</code>

--------------------


### Interfaces


#### CapacitorFacebookAdInitializationOptions

| Prop                               | Type                 | Description                                            | Default            |
| ---------------------------------- | -------------------- | ------------------------------------------------------ | ------------------ |
| **`requestTrackingAuthorization`** | <code>boolean</code> | Use or not requestTrackingAuthorization in iOS(&gt;14) |                    |
| **`testingDevices`**               | <code>{}</code>      |                                                        |                    |
| **`initializeForTesting`**         | <code>boolean</code> |                                                        | <code>false</code> |

</docgen-api>
