package com.alongo.discordbot.domain.messagehandlers.audio;

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
public final class PauseAudioMessageHandler_Factory implements Factory<PauseAudioMessageHandler> {
  private final Provider<LavaPlayerClient> lavaPlayerClientProvider;

  private final Provider<PlayerStorage> playerStorageProvider;

  public PauseAudioMessageHandler_Factory(Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    this.lavaPlayerClientProvider = lavaPlayerClientProvider;
    this.playerStorageProvider = playerStorageProvider;
  }

  @Override
  public PauseAudioMessageHandler get() {
    return newInstance(lavaPlayerClientProvider.get(), playerStorageProvider.get());
  }

  public static PauseAudioMessageHandler_Factory create(
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    return new PauseAudioMessageHandler_Factory(lavaPlayerClientProvider, playerStorageProvider);
  }

  public static PauseAudioMessageHandler newInstance(LavaPlayerClient lavaPlayerClient,
      PlayerStorage playerStorage) {
    return new PauseAudioMessageHandler(lavaPlayerClient, playerStorage);
  }
}
