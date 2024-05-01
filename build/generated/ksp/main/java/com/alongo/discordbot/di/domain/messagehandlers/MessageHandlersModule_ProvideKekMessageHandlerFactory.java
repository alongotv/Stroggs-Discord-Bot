package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.domain.messagehandlers.misc.KekMessageHandler;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class MessageHandlersModule_ProvideKekMessageHandlerFactory implements Factory<KekMessageHandler> {
  private final MessageHandlersModule module;

  public MessageHandlersModule_ProvideKekMessageHandlerFactory(MessageHandlersModule module) {
    this.module = module;
  }

  @Override
  public KekMessageHandler get() {
    return provideKekMessageHandler(module);
  }

  public static MessageHandlersModule_ProvideKekMessageHandlerFactory create(
      MessageHandlersModule module) {
    return new MessageHandlersModule_ProvideKekMessageHandlerFactory(module);
  }

  public static KekMessageHandler provideKekMessageHandler(MessageHandlersModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideKekMessageHandler());
  }
}
