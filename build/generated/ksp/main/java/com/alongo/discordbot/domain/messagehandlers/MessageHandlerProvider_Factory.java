package com.alongo.discordbot.domain.messagehandlers;

import com.alongo.discordbot.domain.messagehandlers.audio.PauseAudioMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.audio.PlayAudioMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.audio.ResumeAudioMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.audio.StopAudioMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.misc.HelpMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.misc.KekMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.qr.QrDecodeMessageHandler;
import com.alongo.discordbot.domain.messagehandlers.qr.QrEncodeMessageHandler;
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
public final class MessageHandlerProvider_Factory implements Factory<MessageHandlerProvider> {
  private final Provider<KekMessageHandler> kekMessageHandlerProvider;

  private final Provider<QrEncodeMessageHandler> qrEncodeMessageHandlerProvider;

  private final Provider<QrDecodeMessageHandler> qrDecodeMessageHandlerProvider;

  private final Provider<HelpMessageHandler> helpMessageHandlerProvider;

  private final Provider<PlayAudioMessageHandler> playAudioMessageHandlerProvider;

  private final Provider<StopAudioMessageHandler> stopAudioMessageHandlerProvider;

  private final Provider<PauseAudioMessageHandler> pauseAudioMessageHandlerProvider;

  private final Provider<ResumeAudioMessageHandler> resumeAudioMessageHandlerProvider;

  public MessageHandlerProvider_Factory(Provider<KekMessageHandler> kekMessageHandlerProvider,
      Provider<QrEncodeMessageHandler> qrEncodeMessageHandlerProvider,
      Provider<QrDecodeMessageHandler> qrDecodeMessageHandlerProvider,
      Provider<HelpMessageHandler> helpMessageHandlerProvider,
      Provider<PlayAudioMessageHandler> playAudioMessageHandlerProvider,
      Provider<StopAudioMessageHandler> stopAudioMessageHandlerProvider,
      Provider<PauseAudioMessageHandler> pauseAudioMessageHandlerProvider,
      Provider<ResumeAudioMessageHandler> resumeAudioMessageHandlerProvider) {
    this.kekMessageHandlerProvider = kekMessageHandlerProvider;
    this.qrEncodeMessageHandlerProvider = qrEncodeMessageHandlerProvider;
    this.qrDecodeMessageHandlerProvider = qrDecodeMessageHandlerProvider;
    this.helpMessageHandlerProvider = helpMessageHandlerProvider;
    this.playAudioMessageHandlerProvider = playAudioMessageHandlerProvider;
    this.stopAudioMessageHandlerProvider = stopAudioMessageHandlerProvider;
    this.pauseAudioMessageHandlerProvider = pauseAudioMessageHandlerProvider;
    this.resumeAudioMessageHandlerProvider = resumeAudioMessageHandlerProvider;
  }

  @Override
  public MessageHandlerProvider get() {
    return newInstance(kekMessageHandlerProvider.get(), qrEncodeMessageHandlerProvider.get(), qrDecodeMessageHandlerProvider.get(), helpMessageHandlerProvider.get(), playAudioMessageHandlerProvider.get(), stopAudioMessageHandlerProvider.get(), pauseAudioMessageHandlerProvider.get(), resumeAudioMessageHandlerProvider.get());
  }

  public static MessageHandlerProvider_Factory create(
      Provider<KekMessageHandler> kekMessageHandlerProvider,
      Provider<QrEncodeMessageHandler> qrEncodeMessageHandlerProvider,
      Provider<QrDecodeMessageHandler> qrDecodeMessageHandlerProvider,
      Provider<HelpMessageHandler> helpMessageHandlerProvider,
      Provider<PlayAudioMessageHandler> playAudioMessageHandlerProvider,
      Provider<StopAudioMessageHandler> stopAudioMessageHandlerProvider,
      Provider<PauseAudioMessageHandler> pauseAudioMessageHandlerProvider,
      Provider<ResumeAudioMessageHandler> resumeAudioMessageHandlerProvider) {
    return new MessageHandlerProvider_Factory(kekMessageHandlerProvider, qrEncodeMessageHandlerProvider, qrDecodeMessageHandlerProvider, helpMessageHandlerProvider, playAudioMessageHandlerProvider, stopAudioMessageHandlerProvider, pauseAudioMessageHandlerProvider, resumeAudioMessageHandlerProvider);
  }

  public static MessageHandlerProvider newInstance(KekMessageHandler kekMessageHandler,
      QrEncodeMessageHandler qrEncodeMessageHandler, QrDecodeMessageHandler qrDecodeMessageHandler,
      HelpMessageHandler helpMessageHandler, PlayAudioMessageHandler playAudioMessageHandler,
      StopAudioMessageHandler stopAudioMessageHandler,
      PauseAudioMessageHandler pauseAudioMessageHandler,
      ResumeAudioMessageHandler resumeAudioMessageHandler) {
    return new MessageHandlerProvider(kekMessageHandler, qrEncodeMessageHandler, qrDecodeMessageHandler, helpMessageHandler, playAudioMessageHandler, stopAudioMessageHandler, pauseAudioMessageHandler, resumeAudioMessageHandler);
  }
}
