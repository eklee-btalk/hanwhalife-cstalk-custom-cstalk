package spectra.attic.btalk.cstalk.faqagent.messaging.event;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.btalk.cstalk.faqagent.domain.enumtype.FaqMessageType;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public abstract class FaqAgentEvent implements JsonSerializable {
    private String tenantId;

    private String partitionId;

    private FaqMessageType faqMessageType;

    protected FaqAgentEvent(String tenantId, String partitionId, FaqMessageType faqMessageType) {
        this.tenantId = tenantId;
        this.partitionId = partitionId;
        this.faqMessageType = faqMessageType;
    }

    @Override
    public String toString() {
        return toJson();
    }
}
