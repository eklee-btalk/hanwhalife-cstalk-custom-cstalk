package spectra.attic.btalk.cstalk.faqagent.messaging.publisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentChatted;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentStarted;
import spectra.attic.coreasset.share.messaging.publisher.KafkaAsyncPublisher;
import spectra.attic.coreasset.share.util.JsonSerializable;

@ExtendWith(MockitoExtension.class)
class CustomFaqAgentEventPublisherTest {
    @InjectMocks
    private CustomFaqAgentEventPublisher customFaqAgentEventPublisher;

    @Mock
    private KafkaAsyncPublisher kafkaAsyncPublisher;

    @Test
    void start() {
        customFaqAgentEventPublisher.start(FaqAgentStarted.sample());
        verify(kafkaAsyncPublisher).send(anyString(), anyString(), any(JsonSerializable.class));
    }

    @Test
    void chat() {
        customFaqAgentEventPublisher.chat(FaqAgentChatted.sample());
        verify(kafkaAsyncPublisher).send(anyString(), anyString(), any(JsonSerializable.class));
    }
}