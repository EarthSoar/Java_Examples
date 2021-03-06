package damain;

import java.math.BigDecimal;

public class Product {
	private Long id;
	private String productName;
	private String brand;// 制造商
	private String supplier;// 供应商
	private BigDecimal salePrice;
	private BigDecimal costPrice;
	private Double cutoff;
	private Long dir_id;


	public Product() {
	}
	public Product(Long id,String productName,BigDecimal salePrice) {
		this.id = id;
		this.productName = productName;
		this.salePrice = salePrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Double getCutoff() {
		return cutoff;
	}

	public void setCutoff(Double cutoff) {
		this.cutoff = cutoff;
	}

	public Long getDir_id() {
		return dir_id;
	}

	public void setDir_id(Long dir_id) {
		this.dir_id = dir_id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", brand=" + brand + ", supplier=" + supplier
				+ ", salePrice=" + salePrice + ", costPrice=" + costPrice + ", cutoff=" + cutoff + ", dir_id=" + dir_id
				+ "]";
	}

	public Product(Long id, String productName, String brand, String supplier, BigDecimal salePrice,
			BigDecimal costPrice, Double cutoff, Long dir_id) {
		super();
		this.id = id;
		this.productName = productName;
		this.brand = brand;
		this.supplier = supplier;
		this.salePrice = salePrice;
		this.costPrice = costPrice;
		this.cutoff = cutoff;
		this.dir_id = dir_id;
	}

	public Product(String productName, String brand, String supplier, BigDecimal salePrice, BigDecimal costPrice,
			Double cutoff, Long dir_id) {
		this.productName = productName;
		this.brand = brand;
		this.supplier = supplier;
		this.salePrice = salePrice;
		this.costPrice = costPrice;
		this.cutoff = cutoff;
		this.dir_id = dir_id;
	}

}
