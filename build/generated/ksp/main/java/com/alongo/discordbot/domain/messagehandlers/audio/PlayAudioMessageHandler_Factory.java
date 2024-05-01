package com.alongo.discordbot.domain.messagehandlers.audio;

import com.alongo.discordbot.data.audio.KordAudioConnectionClient;
import com.alongo.discordbot.data.audio.LavaPlayerClient;
import com.alongo.discordbot.data.audio.LavaPlayerQueryWrapper;
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
public final class PlayAudioMessageHandler_Factory implements Factory<PlayAudioMessageHandler> {
  private final Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider;

  private final Provider<LavaPlayerClient> lavaPlayerClientProvider;

  private final Provider<PlayerStorage> playerStorageProvider;

  private final Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider;

  public PlayAudioMessageHandler_Factory(
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider,
      Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider) {
    this.kordAudioConnectionClientProvider = kordAudioConnectionClientProvider;
    this.lavaPlayerClientProvider = lavaPlayerClientProvider;
    this.playerStorageProvider = playerStorageProvider;
    this.lavaPlayerQueryWrapperProvider = lavaPlayerQueryWrapperProvider;
  }

  @Override
  public PlayAudioMessageHandler get() {
    return newInstance(kordAudioConnectionClientProvider.get(), lavaPlayerClientProvider.get(), playerStorageProvider.get(), lavaPlayerQueryWrapperProvider.get());
  }

  public static PlayAudioMessageHandler_Factory create(
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider,
      Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider) {
    return new PlayAudioMessageHandler_Factory(kordAudioConnectionClientProvider, lavaPlayerClientProvider, playerStorageProvider, lavaPlayerQueryWrapperProvider);
  }

  public static PlayAudioMessageHandler newInstance(
      KordAudioConnectionClient kordAudioConnectionClient, LavaPlayerClient lavaPlayerClient,
      PlayerStorage playerStorage, LavaPlayerQueryWrapper lavaPlayerQueryWrapper) {
    return new PlayAudioMessageHandler(kordAudioConnectionClient, lavaPlayerClient, playerStorage, lavaPlayerQueryWrapper);
  }
}
