package com.core.jdk8.grammar;

/**
 *
 */
@FunctionalInterface
public interface IConvert<F,T> {

	T convert(F form);
}
