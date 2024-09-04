package spectra.attic.btalk.cstalk.faqagent.messaging;

import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentChatted;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentStarted;

public interface CustomFaqAgentEvents {
    void start(FaqAgentStarted started);

    void chat(FaqAgentChatted chatted);
}
