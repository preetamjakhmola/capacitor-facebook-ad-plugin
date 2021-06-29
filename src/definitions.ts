export interface CapacitorFacebookAdPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
