package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.domain.messagehandlers.qr.QrDecodeMessageHandler;
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase;
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
public final class MessageHandlersModule_ProvideQrDecodeMessageHandlerFactory implements Factory<QrDecodeMessageHandler> {
  private final MessageHandlersModule module;

  private final Provider<ResolveQrCodeUseCase> resolveQrCodeUseCaseProvider;

  public MessageHandlersModule_ProvideQrDecodeMessageHandlerFactory(MessageHandlersModule module,
      Provider<ResolveQrCodeUseCase> resolveQrCodeUseCaseProvider) {
    this.module = module;
    this.resolveQrCodeUseCaseProvider = resolveQrCodeUseCaseProvider;
  }

  @Override
  public QrDecodeMessageHandler get() {
    return provideQrDecodeMessageHandler(module, resolveQrCodeUseCaseProvider.get());
  }

  public static MessageHandlersModule_ProvideQrDecodeMessageHandlerFactory create(
      MessageHandlersModule module, Provider<ResolveQrCodeUseCase> resolveQrCodeUseCaseProvider) {
    return new MessageHandlersModule_ProvideQrDecodeMessageHandlerFactory(module, resolveQrCodeUseCaseProvider);
  }

  public static QrDecodeMessageHandler provideQrDecodeMessageHandler(MessageHandlersModule instance,
      ResolveQrCodeUseCase resolveQrCodeUseCase) {
    return Preconditions.checkNotNullFromProvides(instance.provideQrDecodeMessageHandler(resolveQrCodeUseCase));
  }
}
