package com.alongo.discordbot.domain.messagehandlers.audio;

import com.alongo.discordbot.data.audio.KordAudioConnectionClient;
import com.alongo.discordbot.data.audio.LavaPlayerClient;
import com.alongo.discordbot.data.datasource.PlayerStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class StopAudioMessageHandler_Factory implements Factory<StopAudioMessageHandler> {
  private final Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider;

  private final Provider<LavaPlayerClient> lavaPlayerClientProvider;

  private final Provider<PlayerStorage> playerStorageProvider;

  public StopAudioMessageHandler_Factory(
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    this.kordAudioConnectionClientProvider = kordAudioConnectionClientProvider;
    this.lavaPlayerClientProvider = lavaPlayerClientProvider;
    this.playerStorageProvider = playerStorageProvider;
  }

  @Override
  public StopAudioMessageHandler get() {
    return newInstance(kordAudioConnectionClientProvider.get(), lavaPlayerClientProvider.get(), playerStorageProvider.get());
  }

  public static StopAudioMessageHandler_Factory create(
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    return new StopAudioMessageHandler_Factory(kordAudioConnectionClientProvider, lavaPlayerClientProvider, playerStorageProvider);
  }

  public static StopAudioMessageHandler newInstance(
      KordAudioConnectionClient kordAudioConnectionClient, LavaPlayerClient lavaPlayerClient,
      PlayerStorage playerStorage) {
    return new StopAudioMessageHandler(kordAudioConnectionClient, lavaPlayerClient, playerStorage);
  }
}
