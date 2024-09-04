package spectra.attic.btalk.cstalk.faqagent.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import spectra.attic.btalk.cstalk.faqagent.messaging.CustomFaqAgentEvents;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentChatted;
import spectra.attic.btalk.cstalk.faqagent.messaging.event.FaqAgentStarted;
import spectra.attic.btalk.cstalk.faqagent.sdo.FaqMessageRdo;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.application", name = "name", havingValue = "cstalk")
public class CustomFaqAgentAspect {
    @Value("${attic.tenant-id:no_tenant}")
    private String tenantId;

    private final CustomFaqAgentEvents customFaqAgentEvents;

    @AfterReturning(
        pointcut = "execution(* spectra.attic.btalk.cstalk.faqagent.service.flow.impl.FaqAgentFlowServiceImpl.findFirst(..))",
        returning = "faqMessageRdo"
    )
    public void start(JoinPoint joinPoint, FaqMessageRdo faqMessageRdo) {
        customFaqAgentEvents.start(new FaqAgentStarted(tenantId, faqMessageRdo.getPartitionId()));
    }


    @AfterReturning(
        pointcut = "execution(* spectra.attic.btalk.cstalk.faqagent.service.flow.impl.FaqAgentFlowServiceImpl.find(spectra.attic.coreasset.share.domain.PartitionKey, String, String, String))",
        returning = "faqMessageRdo"
    )
    public void chat(JoinPoint joinPoint, FaqMessageRdo faqMessageRdo) {
        customFaqAgentEvents.chat(
            new FaqAgentChatted(
                tenantId,
                faqMessageRdo.getPartitionId(),
                faqMessageRdo.getCategoryId(),
                faqMessageRdo.getFaqId(),
                faqMessageRdo.getMessageType()
            )
        );
    }
}
