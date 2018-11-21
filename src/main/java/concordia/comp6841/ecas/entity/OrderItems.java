package concordia.comp6841.ecas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long r_product_id;
	private String sku;
	private String product_name;
	private Double product_price;
	private Integer item_qty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getR_product_id() {
		return r_product_id;
	}

	public void setR_product_id(Long r_product_id) {
		this.r_product_id = r_product_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Double product_price) {
		this.product_price = product_price;
	}

	public Integer getItem_qty() {
		return item_qty;
	}

	public void setItem_qty(Integer item_qty) {
		this.item_qty = item_qty;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", r_product_id=" + r_product_id + ", sku=" + sku + ", product_name="
				+ product_name + ", product_price=" + product_price + ", item_qty=" + item_qty + "]";
	}

}
