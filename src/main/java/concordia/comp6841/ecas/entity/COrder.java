package concordia.comp6841.ecas.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class COrder {

	@Id
	private Long id;

	private String customer_email;
	private String customer_name;
	private Double order_total;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_orderitems", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "orderitems_id", referencedColumnName = "id"))
	private Collection<OrderItems> order_items;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_billing", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "billing_id", referencedColumnName = "id"))
	private Collection<BillingAddress> billing;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_shipping", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "shipping_id", referencedColumnName = "id"))
	private Collection<ShippingAddress> shipping;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Double getOrder_total() {
		return order_total;
	}

	public void setOrder_total(Double order_total) {
		this.order_total = order_total;
	}

	public Collection<OrderItems> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(Collection<OrderItems> order_items) {
		this.order_items = order_items;
	}

	public Collection<BillingAddress> getBilling() {
		return billing;
	}

	public void setBilling(Collection<BillingAddress> billing) {
		this.billing = billing;
	}

	public Collection<ShippingAddress> getShipping() {
		return shipping;
	}

	public void setShipping(Collection<ShippingAddress> shipping) {
		this.shipping = shipping;
	}

	@Override
	public String toString() {
		return "COrder [id=" + id + ", customer_email=" + customer_email + ", customer_name=" + customer_name
				+ ", order_total=" + order_total + ", order_items=" + order_items + ", billing=" + billing
				+ ", shipping=" + shipping + "]";
	}

}
