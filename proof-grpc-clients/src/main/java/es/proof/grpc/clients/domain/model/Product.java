package es.proof.grpc.clients.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	private Integer productId;
	
	private String name;	
	
	//private LocalDateTime createAt;
	
	//private LocalDateTime updateAt;

}
