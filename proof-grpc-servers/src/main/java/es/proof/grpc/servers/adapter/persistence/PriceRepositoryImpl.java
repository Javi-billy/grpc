package es.proof.grpc.servers.adapter.persistence;

import static java.util.stream.StreamSupport.stream;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.proof.grpc.servers.adapter.mapper.ResultMapper;
import es.proof.grpc.servers.adapter.persistence.entity.PriceEntity;
import es.proof.grpc.servers.domain.model.Price;
import es.proof.grpc.servers.domain.model.PriceRequest;
import es.proof.grpc.servers.domain.repository.PriceRespository;

@Repository
public class PriceRepositoryImpl implements PriceRespository<Price> {

	private PriceEntityRepository priceCrudRepository;

	private ResultMapper mapper;

	public PriceRepositoryImpl(final PriceEntityRepository priceCrudRepository, final ResultMapper mapper) {
		this.priceCrudRepository = priceCrudRepository;
		this.mapper = mapper;
	}

	@Override
	public Price findProductActive(LocalDateTime startDate, Integer productId, String brandId) {
		List<PriceEntity> prices = priceCrudRepository.findByProductIdAndBrandIdOrderByPriorityDesc(productId, brandId);

		/*PriceEntity result = prices.stream()
				.filter(p -> startDate.isEqual(p.getStartDate()) || startDate.isEqual(p.getEndDate())
						|| (startDate.isAfter(p.getStartDate()) && startDate.isBefore(p.getEndDate())))
				.findFirst().orElse(null);

		return mapper.mapToPrice(result);*/
		return null;
	}

	@Override
	public Price findById(Integer id) {		
		var price = priceCrudRepository.findByProductId(id);
		return mapper.mapToPrice(price);
	}

	@Override
	public Price create(PriceRequest p) {		
		var price = priceCrudRepository.save(mapper.mapToPriceEntity(p));
		return mapper.mapToPrice(price);
	}

	@Override
	public List<Price> findAll() {
		return getCollect(priceCrudRepository.findAll());
	}
	
	private List<Price> getCollect(final Iterable<PriceEntity> productIter) {
		 return stream(productIter
				 .spliterator(), false)
				 .map(p -> mapper.mapToPrice(p)).toList();				 
	 }

}
