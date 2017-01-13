package at.rs.alexa.sisyphus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This will execute a given task. It will further more retry if this task failed for a certain amount of times.
 */
public class Worker<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);

    private static final int RETRY_COUNT = 5;

    public Optional<T> execute(Task<T> task) {

        for (int i = 0; i < RETRY_COUNT; i++) {

            try {
                return Optional.of(task.execute());
            } catch (Exception ex) {
                LOGGER.warn("Failed to execute task. Retrying with current RetryCount: " + i , ex);
            }
        }

        LOGGER.error("Failed to execute task. Retry count exhausted");

        return Optional.empty();
    }

}
