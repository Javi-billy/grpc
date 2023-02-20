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
public class PriceGrpc {

	private String brandId;

	//private LocalDateTime startDate;

	private Integer priceList;

	private Integer productId;

	private Double priceProduct;
}
