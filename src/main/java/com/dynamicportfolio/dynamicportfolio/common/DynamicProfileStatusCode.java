package com.dynamicportfolio.dynamicportfolio.common;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DynamicProfileStatusCode implements AppCode<DynamicProfileStatusCode>{

  SUCCESS(1000, "SUCCESS"),
  PROCESSING_ERROR(999, "PROCESSING_ERROR"),
  NULL_FIELD(101, "NULL_FIELD"),
  USER_ALREADY_EXISTS(102, "USER_ALREADY_EXISTS"),
  USER_DOES_NOT_EXIST(103, "USER_DOES_NOT_EXIST"),
  INVALID_DETAILS(104, "INVALID_DETAILS");


  private static Map<Integer, DynamicProfileStatusCode> FORMAT_MAP =
      Stream
          .of(DynamicProfileStatusCode.values()).collect(Collectors.toMap(s -> s.code, Function.identity()));

  private final int code;

  private final String desc;

  DynamicProfileStatusCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  @JsonCreator // This is the factory method and must be static
  public static DynamicProfileStatusCode fromJson(CodeDesc codeDesc) {
    return Optional.ofNullable(FORMAT_MAP.get(codeDesc.getCode())).orElseThrow(() -> new IllegalArgumentException(codeDesc.toString()));
  }

  // validity of status code
  public static Boolean validType(String type) {
    if (FORMAT_MAP.containsKey(type)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * Return the integer code of this status code.
   */
  public int getCode() {
    return this.code;
  }

  /**
   * Return the reason phrase of this status code.
   */
  public String getDesc() {
    return desc;
  }

  /**
   * Return a string representation of this status code.
   */
  @Override
  public String toString() {
    return Integer.toString(code);
  }

  /**
   * @param statusCode
   *
   * @return
   */
  @Override
  public DynamicProfileStatusCode valueOf(int statusCode) {
    for (DynamicProfileStatusCode status : values()) {
      if (status.code == statusCode) {
        return status;
      }
    }
    throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
  }
}
