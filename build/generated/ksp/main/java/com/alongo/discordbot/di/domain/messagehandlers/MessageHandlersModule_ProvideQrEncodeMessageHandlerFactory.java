package com.alongo.discordbot.di.domain.messagehandlers;

import com.alongo.discordbot.domain.messagehandlers.qr.QrEncodeMessageHandler;
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario;
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
public final class MessageHandlersModule_ProvideQrEncodeMessageHandlerFactory implements Factory<QrEncodeMessageHandler> {
  private final MessageHandlersModule module;

  private final Provider<GenerateQrCodeFromTextScenario> generateQrCodeFromTextScenarioProvider;

  public MessageHandlersModule_ProvideQrEncodeMessageHandlerFactory(MessageHandlersModule module,
      Provider<GenerateQrCodeFromTextScenario> generateQrCodeFromTextScenarioProvider) {
    this.module = module;
    this.generateQrCodeFromTextScenarioProvider = generateQrCodeFromTextScenarioProvider;
  }

  @Override
  public QrEncodeMessageHandler get() {
    return provideQrEncodeMessageHandler(module, generateQrCodeFromTextScenarioProvider.get());
  }

  public static MessageHandlersModule_ProvideQrEncodeMessageHandlerFactory create(
      MessageHandlersModule module,
      Provider<GenerateQrCodeFromTextScenario> generateQrCodeFromTextScenarioProvider) {
    return new MessageHandlersModule_ProvideQrEncodeMessageHandlerFactory(module, generateQrCodeFromTextScenarioProvider);
  }

  public static QrEncodeMessageHandler provideQrEncodeMessageHandler(MessageHandlersModule instance,
      GenerateQrCodeFromTextScenario generateQrCodeFromTextScenario) {
    return Preconditions.checkNotNullFromProvides(instance.provideQrEncodeMessageHandler(generateQrCodeFromTextScenario));
  }
}
