package leaseplan.common;

/**
 * Enumeration representing different endpoints.
 * @Author: Sathya
 */
public enum EndPoint {
	
	/**
	 * The search endpoint.
	 */
	SEARCH("/search/demo/");
	
	private final String pathFragment;
	
	/**
	 * Constructs an EndPoint with the specified path fragment.
	 * 
	 * @param pathFragment the path fragment of the endpoint
	 */
	EndPoint(String pathFragment){
		this.pathFragment=pathFragment;
	}
	
	/**
	 * Returns the string representation of the endpoint.
	 * 
	 * @return the string representation of the endpoint
	 */
	@Override
	public String toString() {
		return this.pathFragment;
	}
	
	/**
	 * Looks up an EndPoint based on the given path.
	 * 
	 * @param path the path to look up
	 * @return the string representation of the corresponding EndPoint, or null if not found
	 */
	public static String lookUpFromString(String path) {
		for (EndPoint endpoint : EndPoint.values()) {
			if (endpoint.pathFragment.equalsIgnoreCase(path)) {
				return endpoint.toString();
			}
		}
		return null;
	}
}
