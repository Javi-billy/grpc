package es.proof.grpc.servers.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;

public interface SearchService<P extends Price>{

	Price searchPricesCriteria(LocalDateTime startDate, Integer productId, String brandId);
	
	Price getPriceById(Integer productId);
	
	Price create(PriceRequest price);
	
	List<Price> getAllPrice();

}
