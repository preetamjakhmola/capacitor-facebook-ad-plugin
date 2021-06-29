import { WebPlugin } from '@capacitor/core';

import type { CapacitorFacebookAdPlugin } from './definitions';

export class CapacitorFacebookAdWeb extends WebPlugin implements CapacitorFacebookAdPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
