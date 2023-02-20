package es.proof.grpc.servers.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceRequest {

	private String brandId;

	private LocalDateTime startDate;
	
	private LocalDateTime endDate;

	private Integer priceList;

	private Integer productId;

	private Double priceProduct;
	
	

}
