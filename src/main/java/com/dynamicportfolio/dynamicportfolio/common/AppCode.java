package com.dynamicportfolio.dynamicportfolio.common;

import com.fasterxml.jackson.annotation.JsonValue;

public interface AppCode<T extends Enum<T>> {
  T valueOf(int var1);

  int getCode();

  String getDesc();

  @JsonValue
  default CodeDesc toJson() {
    CodeDesc codeDesc = new CodeDesc();
    codeDesc.setDesc(this.getDesc());
    codeDesc.setCode(this.getCode());
    return codeDesc;
  }
}
