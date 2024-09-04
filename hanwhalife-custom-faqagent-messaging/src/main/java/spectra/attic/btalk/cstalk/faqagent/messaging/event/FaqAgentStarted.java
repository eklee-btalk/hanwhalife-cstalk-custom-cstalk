package spectra.attic.btalk.cstalk.faqagent.messaging.event;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.btalk.cstalk.faqagent.domain.enumtype.FaqMessageType;
import spectra.attic.coreasset.share.domain.PartitionKey;
import spectra.attic.coreasset.share.util.SamplePrinter;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class FaqAgentStarted extends FaqAgentEvent {
    public FaqAgentStarted(String tenantId, String partitionId) {
        super(tenantId, partitionId, FaqMessageType.GREETING);
    }

    public static FaqAgentStarted sample() {
        return new FaqAgentStarted(PartitionKey.sample().getTenantId(), PartitionKey.sample().getPartitionId());
    }

    public static void main(String[] args) {
        SamplePrinter.println(sample());
    }

    @Override
    public String toString() {
        return toJson();
    }
}
