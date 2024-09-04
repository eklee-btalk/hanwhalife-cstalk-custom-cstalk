package spectra.attic.btalk.cstalk.faqagent.messaging.event;

import java.util.UUID;

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
public class FaqAgentChatted extends FaqAgentEvent {
    private String categoryId;

    private String faqId;

    public FaqAgentChatted(String tenantId, String partitionId, String categoryId, String faqId, FaqMessageType faqMessageType) {
        super(tenantId, partitionId, faqMessageType);
        this.categoryId = categoryId;
        this.faqId = faqId;
    }

    public static FaqAgentChatted sample() {
        return new FaqAgentChatted(
            PartitionKey.sample().getTenantId(),
            PartitionKey.sample().getPartitionId(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            FaqMessageType.GREETING
        );
    }

    public static void main(String[] args) {
        SamplePrinter.println(sample());
    }

    @Override
    public String toString() {
        return toJson();
    }
}
