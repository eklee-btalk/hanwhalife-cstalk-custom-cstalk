package spectra.attic.btalk.cstalk.faqagent.messaging.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spectra.attic.btalk.cstalk.faqagent.messaging.CustomFaqAgentEvents;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentChatted;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentEvent;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentStarted;
import spectra.attic.btalk.cstalk.faqagent.messaging.topic.CustomFaqAgentTopic;
import spectra.attic.coreasset.share.messaging.publisher.KafkaAsyncPublisher;


@Component
@RequiredArgsConstructor
public class CustomFaqAgentEventPublisher implements CustomFaqAgentEvents {
    private final KafkaAsyncPublisher kafkaAsyncPublisher;

    @Override
    public void start(FaqAgentStarted started) {
        send(started);
    }

    @Override
    public void chat(FaqAgentChatted chatted) {
        send(chatted);
    }

    private void send(FaqAgentEvent event) {
        kafkaAsyncPublisher.send(
            CustomFaqAgentTopic.EVENT_CUSTOM_FAQ_AGENT,
            event.getPartitionId(),
            event
        );
    }
}
