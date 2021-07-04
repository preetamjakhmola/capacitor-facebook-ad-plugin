import { IBannerDefinitions } from "./banner";
import { InterstitialDefinitions } from "./interstitial";
import { IRewardDefinitions } from "./reward";

type CapacitorFacebookAdDefinitions = IBannerDefinitions & IRewardDefinitions &
  InterstitialDefinitions;

export interface CapacitorFacebookAdPlugin extends CapacitorFacebookAdDefinitions {
  initialize(options: CapacitorFacebookAdInitializationOptions): Promise<void>;

}
export interface CapacitorFacebookAdInitializationOptions {
  /**
   * Use or not requestTrackingAuthorization in iOS(>14)
   * 
   */
  requestTrackingAuthorization?: boolean;

  /* 
   */
  testingDevices?: string[];

  /**
   * @default false 
   */
  initializeForTesting?: boolean;
}