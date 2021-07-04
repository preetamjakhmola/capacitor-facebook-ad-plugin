import { AdError, AdLoadInfo, AdOptions } from "../shared";
import type { PluginListenerHandle } from '@capacitor/core';
import { AdRewardItem } from "./reward-item.interface";
import { RewardAdPluginEvents } from "./reward-ad-plugin-events.enum";
  
export interface IRewardDefinitions{
 /**
   * Prepare a reward video ad
   *
   * @group RewardVideo
   * @param options AdOptions
   * @since 1.1.2
   */
  prepareRewardVideoAd(options: AdOptions): Promise<AdLoadInfo>;

  /**
   * Show a reward video ad
   *
   * @group RewardVideo
   * @since 1.1.2
   */
showRewardVideoAd(): Promise<AdRewardItem>;

addListener(
 eventName: RewardAdPluginEvents.FailedToLoad,
 listenerFunc: (error: AdError) => void,
): PluginListenerHandle;

addListener (
 eventName: RewardAdPluginEvents.Loaded,
 listenerFunc: (info: AdLoadInfo ) => void,
): PluginListenerHandle;

addListener (
 eventName: RewardAdPluginEvents.Rewarded,
 listenerFunc: (reward: AdRewardItem) => void,
): PluginListenerHandle;

addListener(
 eventName: RewardAdPluginEvents.Dismissed,
 listenerFunc: () => void,
): PluginListenerHandle;


addListener(
 eventName: RewardAdPluginEvents.FailedToShow,
 listenerFunc: (error: AdError) => void,
) : PluginListenerHandle;


addListener (
 eventName: RewardAdPluginEvents.Showed,
 listenerFunc: () => void,
): PluginListenerHandle;
}