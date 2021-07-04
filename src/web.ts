import { WebPlugin } from '@capacitor/core';

import type { CapacitorFacebookAdPlugin } from './definitions';
import { AdRewardItem } from './reward';
import { AdLoadInfo, AdOptions } from './shared';

export class CapacitorFacebookAdWeb extends WebPlugin implements CapacitorFacebookAdPlugin  {  

  // async echo(options: { value: string }): Promise<{ value: string }> {
  //   console.log('ECHO', options);
  //   return options;
  // }
  constructor() {
    super({
      name: 'CapacitorFacebookAd',
      platforms: ['web'],
    });
  }
  async initialize(): Promise<void> {
    console.log('initialize');
  }

  async showBanner(options: AdOptions): Promise<void> {
    console.log('showBanner', options);
  }

  // Hide the banner, remove it from screen, but can show it later
  async hideBanner(): Promise<void> {
    console.log('hideBanner');
  }

  // Resume the banner, show it after hide
  async resumeBanner(): Promise<void> {
    console.log('resumeBanner');
  }

  // Destroy the banner, remove it from screen.
  async removeBanner(): Promise<void> {
    console.log('removeBanner');
  }

  async prepareInterstitial(options: AdOptions): Promise<AdLoadInfo> {
    console.log('prepareInterstitial', options);
    return {
      adPlacementId: options.adId,
    };
  }

  async showInterstitial(): Promise<void> {
    console.log('showInterstitial');
  }

  async prepareRewardVideoAd(options: AdOptions): Promise<AdLoadInfo> {
    console.log(options);
    return {
      adPlacementId: options.adId,
    };
  }

  async showRewardVideoAd(): Promise<AdRewardItem> {
    return {
      type: '',
      amount: 0,
    };
  }
}
