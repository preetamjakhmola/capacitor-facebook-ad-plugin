import { registerPlugin } from '@capacitor/core';

import type { CapacitorFacebookAdPlugin } from './definitions';

const CapacitorFacebookAd = registerPlugin<CapacitorFacebookAdPlugin>('CapacitorFacebookAd', {
  web: () => import('./web').then(m => new m.CapacitorFacebookAdWeb()),
});
 
export * from './definitions';
export * from './banner/index';
export * from './interstitial/index';
export * from './reward/index';
export * from './shared/index'; 
export { CapacitorFacebookAd };
