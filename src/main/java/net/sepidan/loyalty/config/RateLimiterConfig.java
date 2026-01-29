package net.sepidan.loyalty.config;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RateLimiterConfig {

  private final RedissonClient redisson;

  /**
   * Get or create a distributed rate limiter with safe initialization.
   */
  public RRateLimiter getLimiter(String key, long permits, long intervalSeconds) {
    RRateLimiter limiter = redisson.getRateLimiter(key);

    // Only set rate if it doesn't exist yet
    if (!limiter.isExists()) {
      limiter.trySetRate(RateType.OVERALL, permits, intervalSeconds, RateIntervalUnit.SECONDS);
    }

    return limiter;
  }
}
