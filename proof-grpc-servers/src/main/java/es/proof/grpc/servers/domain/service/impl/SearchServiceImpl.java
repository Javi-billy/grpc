package es.proof.grpc.servers.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;
import es.proof.grpc.servers.domain.repository.PriceRespository;
import es.proof.grpc.servers.domain.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService<Price> {

	private PriceRespository<Price> repository;

	public SearchServiceImpl(final PriceRespository<Price> repository) {
		this.repository = repository;		
	}

	@Override	
	public Price searchPricesCriteria(LocalDateTime startDate, Integer productId, String brandId) {
		return repository.findProductActive(startDate, productId, brandId);
	}

	@Override
	public Price getPriceById(Integer productId) {
		return repository.findById(productId);
	}

	@Override
	public Price create(PriceRequest price) {
		return repository.create(price);
	}

	@Override
	public List<Price> getAllPrice() {
		return repository.findAll();
	}

}
