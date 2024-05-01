package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.data.audio.KordAudioConnectionClient;
import com.alongo.discordbot.data.audio.LavaPlayerClient;
import com.alongo.discordbot.data.audio.LavaPlayerQueryWrapper;
import com.alongo.discordbot.data.datasource.PlayerStorage;
import com.alongo.discordbot.domain.messagehandlers.audio.PlayAudioMessageHandler;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class MessageHandlersModule_ProvideAudioMessageHandlerFactory implements Factory<PlayAudioMessageHandler> {
  private final MessageHandlersModule module;

  private final Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider;

  private final Provider<LavaPlayerClient> lavaPlayerClientProvider;

  private final Provider<PlayerStorage> playerStorageProvider;

  private final Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider;

  public MessageHandlersModule_ProvideAudioMessageHandlerFactory(MessageHandlersModule module,
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider,
      Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider) {
    this.module = module;
    this.kordAudioConnectionClientProvider = kordAudioConnectionClientProvider;
    this.lavaPlayerClientProvider = lavaPlayerClientProvider;
    this.playerStorageProvider = playerStorageProvider;
    this.lavaPlayerQueryWrapperProvider = lavaPlayerQueryWrapperProvider;
  }

  @Override
  public PlayAudioMessageHandler get() {
    return provideAudioMessageHandler(module, kordAudioConnectionClientProvider.get(), lavaPlayerClientProvider.get(), playerStorageProvider.get(), lavaPlayerQueryWrapperProvider.get());
  }

  public static MessageHandlersModule_ProvideAudioMessageHandlerFactory create(
      MessageHandlersModule module,
      Provider<KordAudioConnectionClient> kordAudioConnectionClientProvider,
      Provider<LavaPlayerClient> lavaPlayerClientProvider,
      Provider<PlayerStorage> playerStorageProvider,
      Provider<LavaPlayerQueryWrapper> lavaPlayerQueryWrapperProvider) {
    return new MessageHandlersModule_ProvideAudioMessageHandlerFactory(module, kordAudioConnectionClientProvider, lavaPlayerClientProvider, playerStorageProvider, lavaPlayerQueryWrapperProvider);
  }

  public static PlayAudioMessageHandler provideAudioMessageHandler(MessageHandlersModule instance,
      KordAudioConnectionClient kordAudioConnectionClient, LavaPlayerClient lavaPlayerClient,
      PlayerStorage playerStorage, LavaPlayerQueryWrapper lavaPlayerQueryWrapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideAudioMessageHandler(kordAudioConnectionClient, lavaPlayerClient, playerStorage, lavaPlayerQueryWrapper));
  }
}
