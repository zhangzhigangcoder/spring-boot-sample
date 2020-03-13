package org.spring.boot.strategy.keygen;

/**
 * Key generator.
 *
 */
public interface KeyGenerator {
    
	/**
     * Get algorithm type.
     * 
     * @return type
     */
	String getType();
	
    /**
     * Generate key.
     * 
     * @return generated key
     */
    Comparable<?> generateKey() throws InterruptedException;
}