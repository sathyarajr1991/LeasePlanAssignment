package leaseplan.pojo.product;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a product.
 * @author Sathya
 */
public class Product {

	public String provider;
	
	public String url;
	
	public String brand;
	
	public double price;
	
	public String unit;
	
	public boolean isPromo;
	
	public String promoDetails;

	public String image;

	@JsonProperty("title")
	private String title;

	/**
	 * Returns the title of the product.
	 *
	 * @return the title of the product
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title of the product.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
