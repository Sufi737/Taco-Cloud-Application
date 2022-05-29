package com.springinaction.tacocloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="taco.orders")
public class TacoConfigHolder {
	
	public int pageSize = 20; //if no config in .yml or .properties set then 20 will be default value

}
