package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.domain.messagehandlers.misc.HelpMessageHandler;
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
public final class MessageHandlersModule_ProvideHelpMessageHandlerFactory implements Factory<HelpMessageHandler> {
  private final MessageHandlersModule module;

  public MessageHandlersModule_ProvideHelpMessageHandlerFactory(MessageHandlersModule module) {
    this.module = module;
  }

  @Override
  public HelpMessageHandler get() {
    return provideHelpMessageHandler(module);
  }

  public static MessageHandlersModule_ProvideHelpMessageHandlerFactory create(
      MessageHandlersModule module) {
    return new MessageHandlersModule_ProvideHelpMessageHandlerFactory(module);
  }

  public static HelpMessageHandler provideHelpMessageHandler(MessageHandlersModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideHelpMessageHandler());
  }
}
