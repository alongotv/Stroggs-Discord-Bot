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
public final class ResumeAudioMessageHandler_Factory implements Factory<ResumeAudioMessageHandler> {
  private final Provider<LavaPlayerClient> lavaPlayerClientProvider;

  private final Provider<PlayerStorage> playerStorageProvider;

  public ResumeAudioMessageHandler_Factory(Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    this.lavaPlayerClientProvider = lavaPlayerClientProvider;
    this.playerStorageProvider = playerStorageProvider;
  }

  @Override
  public ResumeAudioMessageHandler get() {
    return newInstance(lavaPlayerClientProvider.get(), playerStorageProvider.get());
  }

  public static ResumeAudioMessageHandler_Factory create(
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider) {
    return new ResumeAudioMessageHandler_Factory(lavaPlayerClientProvider, playerStorageProvider);
  }

  public static ResumeAudioMessageHandler newInstance(LavaPlayerClient lavaPlayerClient,
      PlayerStorage playerStorage) {
    return new ResumeAudioMessageHandler(lavaPlayerClient, playerStorage);
  }
}
