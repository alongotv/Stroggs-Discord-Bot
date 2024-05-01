package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.data.MessageCreateEventTransmitter;
import com.alongo.discordbot.domain.messagehandlers.CreateMessageEventHandler;
import com.alongo.discordbot.domain.messagehandlers.MessageHandlerProvider;
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
public final class MessageHandlerFactoryModule_ProvideMessageHandlerFactoryFactory implements Factory<CreateMessageEventHandler> {
  private final MessageHandlerFactoryModule module;

  private final Provider<MessageCreateEventTransmitter> messageCreateEventTransmitterProvider;

  private final Provider<MessageHandlerProvider> messageHandlerProvider;

  public MessageHandlerFactoryModule_ProvideMessageHandlerFactoryFactory(
      MessageHandlerFactoryModule module,
      Provider<MessageCreateEventTransmitter> messageCreateEventTransmitterProvider,
      Provider<MessageHandlerProvider> messageHandlerProvider) {
    this.module = module;
    this.messageCreateEventTransmitterProvider = messageCreateEventTransmitterProvider;
    this.messageHandlerProvider = messageHandlerProvider;
  }

  @Override
  public CreateMessageEventHandler get() {
    return provideMessageHandlerFactory(module, messageCreateEventTransmitterProvider.get(), messageHandlerProvider.get());
  }

  public static MessageHandlerFactoryModule_ProvideMessageHandlerFactoryFactory create(
      MessageHandlerFactoryModule module,
      Provider<MessageCreateEventTransmitter> messageCreateEventTransmitterProvider,
      Provider<MessageHandlerProvider> messageHandlerProvider) {
    return new MessageHandlerFactoryModule_ProvideMessageHandlerFactoryFactory(module, messageCreateEventTransmitterProvider, messageHandlerProvider);
  }

  public static CreateMessageEventHandler provideMessageHandlerFactory(
      MessageHandlerFactoryModule instance,
      MessageCreateEventTransmitter messageCreateEventTransmitter,
      MessageHandlerProvider messageHandlerProvider) {
    return Preconditions.checkNotNullFromProvides(instance.provideMessageHandlerFactory(messageCreateEventTransmitter, messageHandlerProvider));
  }
}
