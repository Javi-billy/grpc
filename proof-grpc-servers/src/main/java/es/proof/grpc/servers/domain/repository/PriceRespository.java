package es.proof.grpc.servers.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;

public interface PriceRespository<P extends Price> {

	P findProductActive(LocalDateTime startDate, Integer id, String brandId);
	
	P findById(Integer id);
	
	P create(PriceRequest p);
	
	List<P> findAll();

}
