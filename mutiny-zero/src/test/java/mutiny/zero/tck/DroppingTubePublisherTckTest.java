package mutiny.zero.tck;

import java.util.concurrent.Flow.Publisher;

import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.FlowPublisherVerification;

import mutiny.zero.BackpressureStrategy;
import mutiny.zero.TubeConfiguration;
import mutiny.zero.ZeroPublisher;

public class DroppingTubePublisherTckTest extends FlowPublisherVerification<Long> {

    public DroppingTubePublisherTckTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Long> createFlowPublisher(long elements) {
        TubeConfiguration configuration = new TubeConfiguration().withBackpressureStrategy(BackpressureStrategy.DROP);
        return ZeroPublisher.create(configuration, tube -> TubeEmitLoop.loop(tube, elements));
    }

    @Override
    public Publisher<Long> createFailedFlowPublisher() {
        return null;
    }
}
