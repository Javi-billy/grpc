package es.proof.grpc.clients.adapter.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "productos")
@Data
@Accessors(chain = true)
public class ProductEntity implements Serializable {
	
	private static final long serialVersionUID = -8014875939625649323L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "name")
	private String name;

	/*@Column(name = "create_at")
	private LocalDateTime createAt;

	@Column(name = "update_at")
	private LocalDateTime updateAt;*/
	
}
