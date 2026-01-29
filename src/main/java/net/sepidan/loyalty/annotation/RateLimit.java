package net.sepidan.loyalty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * ============================================================
 *  RATE LIMITING ANNOTATION
 *  ------------------------------------------------------------
 *  This annotation allows you to apply distributed rate-limiting
 *  using Redisson's RRateLimiter. You can apply it:
 *
 *    On a specific method (per-method rate limit)
 *    On a full controller class (apply same or per-method buckets)
 *
 *  The annotation supports:
 *    - Global limits
 *    - IP based limits
 *    - Phone based limits
 *    - User ID based limits
 *
 *  It also supports TWO SCOPES:
 *
 *  . METHOD_ONLY     => each method gets its own rate bucket
 *  . CLASS_UNIFIED   => all methods in class share SAME limit
 *
 *  Redis keys look like:
 *      rl:<keyType>:<bucketName>[:ip/user/phone]
 *
 * ============================================================
 *
 *  BASIC EXAMPLE (method only)
 *  ------------------------------------------------------------
 *  @RateLimit(
 *     name = "login-otp",
 *     key = RateLimit.KeyType.IP,
 *     permits = 3,
 *     per = 1,
 *     unit = TimeUnit.MINUTES
 *  )
 *  public Response sendOtp() { ... }
 *
 *  => Allows 3 OTP requests per IP per minute
 *
 * ============================================================
 *
 *  CLASS-LEVEL EXAMPLE (shared limit across all APIs)
 *  ------------------------------------------------------------
 *  @RateLimit(
 *     name = "auth-global",
 *     key = RateLimit.KeyType.IP,
 *     permits = 20,
 *     per = 1,
 *     unit = TimeUnit.MINUTES,
 *     scope = RateLimit.RateLimitScope.CLASS_UNIFIED
 *  )
 *  @RestController
 *  public class AuthController { ... }
 *
 *  => ALL methods inside AuthController share ONE bucket
 *
 * ============================================================
 *
 *  CLASS-LEVEL + METHOD OVERRIDE EXAMPLE
 *  ------------------------------------------------------------
 *  @RateLimit(name = "auth", permits = 10)
 *  class AuthController {
 *
 *     @RateLimit(name = "otp", permits = 1)
 *     public sendOtp() {}
 *  }
 *
 *  => method-level annotation ALWAYS overrides class-level
 *
 * ============================================================
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RateLimit {

  /**
   * Number of allowed requests within the defined time window.
   *
   * Example:
   *  permits = 5 => allow 5 requests
   */
  long permits() default 10;

  /**
   * The amount of time after which the rate limiter refills the permits.
   *
   * Example:
   *  per = 1 + unit = MINUTES => 1 minute window
   */
  long per() default 1;

  /**
   * The time unit for the "per" value.
   *
   * Typical values:
   *  SECONDS, MINUTES, HOURS
   */
  TimeUnit unit() default TimeUnit.MINUTES;

  /**
   * Determines how the rate limit key is built.
   *
   * GLOBAL => same bucket for everyone
   * IP => bucket = name + IP address
   * PHONE => bucket = name + ?phone request param
   * USER_ID => bucket = name + authenticated user id
   */
  KeyType key() default KeyType.GLOBAL;

  /**
   * Unique Redis bucket identifier.
   * Always choose a meaningful name.
   *
   * Examples:
   *   "login"
   *   "otp"
   *   "register-ip-limit"
   */
  String name() default "global-limit";

  /**
   * Custom error message thrown in RateLimitExceededException.
   */
  String message() default "Too many requests. Please try again later";

  /**
   * Different bucket types for rate-limit scoping.
   */
  enum KeyType {
    GLOBAL,
    IP,
    PHONE,
    USER_ID
  }

  /**
   * Determines how rate-limits behave when annotation is placed on a class.
   *
   * METHOD_ONLY:
   *    Each method gets its own Redis key:
   *        rl:ip:auth:login
   *        rl:ip:auth:register
   *
   * CLASS_UNIFIED:
   *    All methods share ONE Redis key:
   *        rl:ip:auth-global
   */
  RateLimitScope scope() default RateLimitScope.METHOD_ONLY;

  enum RateLimitScope {
    METHOD_ONLY,
    CLASS_UNIFIED
  }
}
